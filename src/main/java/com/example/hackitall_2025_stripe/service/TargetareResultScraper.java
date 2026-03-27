package com.example.hackitall_2025_stripe.service;

import com.example.hackitall_2025_stripe.model.Firma;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TargetareResultScraper {

    public List<Firma> scrapeFirme(String url) throws Exception {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .get();

        List<Firma> firme = new ArrayList<>();

        // aici va trebui să ajustezi selectorii după structura reală a paginii
        Elements companyBlocks = doc.select("a:contains(Vezi toate informațiile)");

        for (Element link : companyBlocks) {
            Element container = link.parent().parent();
            String nume = container.selectFirst("a").text();
            String caenText = container.select("div:contains(CAEN)").text();

            firme.add(new Firma(nume, "", caenText));
        }

        return firme;
    }
}
