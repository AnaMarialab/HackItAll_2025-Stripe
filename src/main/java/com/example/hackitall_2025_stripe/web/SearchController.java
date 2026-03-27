package com.example.hackitall_2025_stripe.web;

import com.example.hackitall_2025_stripe.model.ArticleCategory;
import com.example.hackitall_2025_stripe.model.Firma;
import com.example.hackitall_2025_stripe.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final AiCaenService aiCaenService;
    private final TargetareSearchService searchService;
    private final TargetareResultScraper scraper;

    public SearchController(
            @Value("${openai.api.key}") String apiKey) {
        this.aiCaenService = new AiCaenService(apiKey);
        this.searchService = new TargetareSearchService();
        this.scraper = new TargetareResultScraper();
    }

    @GetMapping
    public List<Firma> search(@RequestParam String articol) throws Exception {
        ArticleCategory category = aiCaenService.classifyArticle(articol);
        var caenCodes = CaenMapper.getCaenCodes(category);
        String url = searchService.buildUrl(caenCodes);
        return scraper.scrapeFirme(url);
    }
}
