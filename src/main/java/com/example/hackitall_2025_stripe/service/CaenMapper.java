package com.example.hackitall_2025_stripe.service;

import com.example.hackitall_2025_stripe.model.ArticleCategory;

import java.util.List;

public class CaenMapper {

    public static List<String> getCaenCodes(ArticleCategory category) {
        return switch (category) {
            case CLOTHES -> List.of("4771", "4791");
            case CRAFTS  -> List.of("4778", "3213");
        };
    }
}
