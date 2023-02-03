package ru.itstamag.application.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itstamag.application.client.hh.HhClient;
import ru.itstamag.application.client.hh.dto.FoundDto;

import java.io.IOException;

@RestController
public class ApplicationController {

    private final HhClient hhClient;

    public ApplicationController(HhClient hhClient) {
        this.hhClient = hhClient;
    }

    @GetMapping("/")
    public FoundDto getInfo() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        Elements newsHeadlines = doc.select("#mp-itn b a");
        StringBuilder sb = new StringBuilder();
        for (Element headline : newsHeadlines) {
            String s = String.format("%s\n\t%s", headline.attr("title"), headline.absUrl("href"));
            sb.append(s).append(System.lineSeparator());
        }
        return hhClient.vacancies();
    }
}
