package com.ai.resume_analyzer.Service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkillExtractorService {

    // 🔹 Minimal stop words (not too aggressive)
    private static final List<String> STOP_WORDS = List.of(
            "the","is","are","and","or","to","for","with","a","an","in","on"
    );

    // 🔹 Synonyms map (AI-like behavior)
    private static final Map<String, List<String>> SYNONYMS = Map.of(
            "rest", List.of("restful", "api", "apis"),
            "sql", List.of("mysql", "postgresql", "database"),
            "spring", List.of("spring boot", "spring framework"),
            "aws", List.of("amazon web services"),
            "js", List.of("javascript")
    );

    public Set<String> extractSkills(String text) {

        // 🔹 Clean text
        text = text.toLowerCase();
        text = text.replaceAll("[^a-zA-Z0-9\\s]", "");

        // 🔹 Tokenize
        List<String> tokens = Arrays.asList(text.split("\\s+"));

        // 🔹 Remove stop words
        List<String> filtered = tokens.stream()
                .filter(word -> !STOP_WORDS.contains(word))
                .filter(word -> word.length() > 2)
                .collect(Collectors.toList());

        // 🔹 Generate n-grams
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < filtered.size() - 1; i++) {
            ngrams.add(filtered.get(i) + " " + filtered.get(i + 1));
        }

        // 🔹 Combine all
        Set<String> result = new HashSet<>();
        result.addAll(filtered);
        result.addAll(ngrams);

        return result;
    }

    // 🔥 SMART MATCHING (AI-like)
    public boolean isSimilar(String a, String b) {

        if (a.equals(b)) return true;

        // partial match
        if (a.contains(b) || b.contains(a)) return true;

        // synonym match
        if (SYNONYMS.containsKey(a) && SYNONYMS.get(a).contains(b)) return true;
        if (SYNONYMS.containsKey(b) && SYNONYMS.get(b).contains(a)) return true;

        return false;
    }
}