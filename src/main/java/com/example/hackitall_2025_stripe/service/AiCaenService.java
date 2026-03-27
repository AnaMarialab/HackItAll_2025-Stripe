package com.example.hackitall_2025_stripe.service;

import com.example.hackitall_2025_stripe.model.ArticleCategory;

public class AiCaenService {

    // păstrăm semnătura constructorului ca să nu stricăm alte clase
    private final String apiKey;

    public AiCaenService(String apiKey) {
        this.apiKey = apiKey; // momentan nu îl folosim
    }

    public ArticleCategory classifyArticle(String userText) {
        if (userText == null) {
            return ArticleCategory.CLOTHES;
        }

        String text = userText.toLowerCase();

        // Cuvinte tipice pentru CLOTHES
        String[] clothesKeywords = {
                "rochie", "tricou", "bluza", "bluză", "pantalon", "pantaloni",
                "fusta", "fustă", "hanorac", "pulover", "geaca", "geacă",
                "palton", "camasa", "cămașă", "pantof", "adidasi", "adidași",
                "tenisi", "cizme", "sandale", "ghete", "botine", "sapca", "șapcă"
        };

        // Cuvinte tipice pentru CRAFTS
        String[] craftsKeywords = {
                "bijuterie", "bijuterii", "inel", "inelus", "bratara", "brățară",
                "colier", "cercei", "decor", "decoratiune", "decorațiune",
                "handmade", "macrame", "crosetat", "croșetat", "broderie",
                "tabloul", "tablou", "lumanare", "lumânare", "sapun", "săpun",
                "obiect artizanal", "artizanal", "suvenir"
        };

        // dacă găsim un cuvânt de haine -> CLOTHES
        for (String k : clothesKeywords) {
            if (text.contains(k)) {
                return ArticleCategory.CLOTHES;
            }
        }

        // dacă găsim un cuvânt de artizanat -> CRAFTS
        for (String k : craftsKeywords) {
            if (text.contains(k)) {
                return ArticleCategory.CRAFTS;
            }
        }

        // fallback: punem CLOTHES ca default
        return ArticleCategory.CLOTHES;
    }
}
