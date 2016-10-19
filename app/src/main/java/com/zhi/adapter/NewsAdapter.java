package com.zhi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhi.domain.News;
import com.zhi.xmlclient.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class NewsAdapter extends BaseAdapter{

    private Context context;
    private List<News> news;

    public NewsAdapter(){

    }

    public NewsAdapter(Context context, List<News> news){
        this.context = context;
        this.news = news;
    }


    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(null == convertView){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.mTvId = (TextView) convertView.findViewById(R.id.tv_id);
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.mTvTimeLength = (TextView) convertView.findViewById(R.id.tv_timeLength);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.mTvId.setText(news.get(position).getId()+"");
        holder.mTvTitle.setText(news.get(position).getTitle());
        holder.mTvTimeLength.setText(news.get(position).getTimeLength()+"");
        return convertView;
    }

    static class Holder {
        TextView mTvId;
        TextView mTvTitle;
        TextView mTvTimeLength;

    }
}
