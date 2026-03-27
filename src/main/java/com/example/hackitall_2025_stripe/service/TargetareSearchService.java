package com.example.hackitall_2025_stripe.service;

import com.fasterxml.jackson.databind.ObjectMapper;  // sau alt typo


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TargetareSearchService {

    private final ObjectMapper mapper = new ObjectMapper();

    public String buildUrl(List<String> caenCodes) throws Exception {
        SearchState state = new SearchState();
        state.query = "";
        state.page = new SearchState.Page();
        state.page.size = 10;
        state.page.from = 0;
        state.sortBy = "";

        List<SearchState.Filter> filters = new ArrayList<>();

        filters.add(simpleFilter("hasWebsite", "da"));
        filters.add(rangeFilter("turnover", 0, 1_000_000));
        filters.add(rangeFilter("netProfit", 50_000, 200_000));
        filters.add(simpleFilter("county", "Municipiul Bucuresti"));
        filters.add(rangeFilter("employee", 0, 50));
        filters.add(simpleFilter("status", "functiune"));
        filters.add(simpleFilter("hasPhone", "da"));

        SearchState.Filter caenFilter = new SearchState.Filter();
        caenFilter.identifier = "includeCaen"; // verifică exact numele în URL
        caenFilter.value = String.join(",", caenCodes);
        filters.add(caenFilter);

        state.filters = filters;

        String json = mapper.writeValueAsString(state);
        String base64 = Base64.getEncoder()
                .encodeToString(json.getBytes(StandardCharsets.UTF_8));
        String encoded = URLEncoder.encode(base64, StandardCharsets.UTF_8);

        return "https://targetare.ro/cauta-firme?state=" + encoded;
    }

    private SearchState.Filter simpleFilter(String id, String value) {
        SearchState.Filter f = new SearchState.Filter();
        f.identifier = id;
        f.value = value;
        return f;
    }

    private SearchState.Filter rangeFilter(String id, int min, int max) {
        SearchState.Filter f = new SearchState.Filter();
        f.identifier = id;
        f.min = min;
        f.max = max;
        return f;
    }
}
