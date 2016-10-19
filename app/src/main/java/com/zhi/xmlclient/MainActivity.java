package com.zhi.xmlclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.zhi.adapter.NewsAdapter;
import com.zhi.domain.News;
import com.zhi.service.NewsService;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private static final int SUCCESS_SHOW = 0x1;
    private static final int FAIL_SHOW = 0x2;

    private List<News> news;

    private EditText mEtUrl;
    private Button mBtnShow;
    private ListView mLvNews;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS_SHOW:
                    showNews();
                    break;
                case FAIL_SHOW:
                    Toast.makeText(MainActivity.this,"xml显示失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvent();
    }

    private void initViews() {
        mEtUrl = (EditText) findViewById(R.id.et_url);
        mBtnShow = (Button) findViewById(R.id.btn_show);
        mLvNews = (ListView) findViewById(R.id.lv_news);
    }

    private void initEvent() {
        mBtnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                show();
                break;
        }
    }

    private void show(){
        new Thread(){
            @Override
            public void run() {
                String path = mEtUrl.getText().toString();
                try {
                    news = NewsService.getNews(path);
                    mHandler.sendEmptyMessage(SUCCESS_SHOW);
                } catch (XmlPullParserException e) {
                    mHandler.sendEmptyMessage(FAIL_SHOW);
                    if(null != e) {
                        e.printStackTrace();
                    }
                } catch (IOException e1) {
                    mHandler.sendEmptyMessage(FAIL_SHOW);
                    if(null != e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void showNews(){
        NewsAdapter adapter = new NewsAdapter(MainActivity.this, news);
        mLvNews.setAdapter(adapter);
    }
}