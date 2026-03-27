package com.example.hackitall_2025_stripe.service;

import java.util.List;

public class SearchState {
    public String query;
    public List<Filter> filters;
    public Page page;
    public String sortBy;

    public static class Filter {
        public String identifier;
        public String value;
        public Integer min;
        public Integer max;
    }

    public static class Page {
        public int size;
        public int from;
    }
}
