package com.harlon.drugmanagement.utils;

import com.harlon.drugmanagement.entity.BaiduNews;
import com.harlon.drugmanagement.exception.customize.GrabNewsException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaiduNewsJsoupUtil {

    public List getFiveNews(int count){

        String url = "http://news.baidu.com";
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url), 30000);
        } catch (IOException e) {
            throw new GrabNewsException();
        }

        Element bigDiv = document.getElementById("pane-news");
        Elements list = bigDiv.getElementsByTag("li");
        List<BaiduNews> news = new ArrayList<>();
        for (int i = 0; i < count; i++ ){
            String title = list.get(i).getElementsByTag("a").eq(0).text();
            String attr = list.get(i).getElementsByTag("a").eq(0).attr("href");
            news.add(new BaiduNews(title,attr));
        }
        return news;
    }
}
