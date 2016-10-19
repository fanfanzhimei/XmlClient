package com.zhi.service;

import android.util.Xml;
import android.widget.Toast;

import com.zhi.domain.News;
import com.zhi.utils.IOUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class NewsService {
    public static List<News> getNews(String path) throws XmlPullParserException, IOException {
        InputStream in = null;
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        int state = connection.getResponseCode();
        if(state == 200) {
            in = connection.getInputStream();
        }
        List<News> list = xmlParse(in);
        connection.disconnect();
        return list;
    }

    private static List<News> xmlParse(InputStream in) throws XmlPullParserException, IOException {
        List<News> news = null;
        News video = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(in, "UTF-8");
        int event = parser.getEventType();
        while(event != XmlPullParser.END_DOCUMENT){
            switch (event){
                case XmlPullParser.START_TAG:
                    if("videos".equals(parser.getName())){
                        news = new ArrayList<News>();
                    }
                    if("video".equals(parser.getName())){
                        video = new News();
                        int id = new Integer(parser.getAttributeValue(0));
                        video.setId(id);
                    }
                    if("title".equals(parser.getName())){
                        String title = parser.nextText();
                        video.setTitle(title);
                    }
                    if("timeLength".equals(parser.getName())){
                        int timeLength = new Integer(parser.nextText());
                        video.setTimeLength(timeLength);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("video".equals(parser.getName())){
                        news.add(video);
                        video = null;
                    }
                    break;
            }
            event = parser.next();
        }
        in.close();
        return news;
    }
}