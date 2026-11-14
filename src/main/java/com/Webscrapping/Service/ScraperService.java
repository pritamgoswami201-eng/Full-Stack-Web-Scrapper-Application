package com.Webscrapping.Service;

import com.Webscrapping.Model.ScrapeResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    public ScrapeResponse getScrape(String url ) throws IOException {
        Document doc = Jsoup.connect(url).get();

        List<String> images = new ArrayList<>();
        List<String> links = new ArrayList<>();
        List<String> videos = new ArrayList<>();

        for(Element img : doc.select("img")){// document ke select korlo and img tag e galo
            String src = img.absUrl("src");// img tag jabar por src te gia absolute url ke nilo and src variable e store korlo . this also same for other two mwthods
            if(!src.isEmpty()){
                images.add((src));
            }
        }
        for(Element video : doc.select("video")){
            String src = video.absUrl("src");
            if(!src.isEmpty()){
                videos.add((src));
            }
        }
        for(Element link : doc.select("a[href]")){
            String href = link.absUrl("href");
            if(!href.isEmpty()){
                links.add((href));
            }
        }
        ScrapeResponse response = new ScrapeResponse();

        response.setImages(images);
        response.setLinks(links);
        response.setVideos(videos);

        return response;
    }
}
