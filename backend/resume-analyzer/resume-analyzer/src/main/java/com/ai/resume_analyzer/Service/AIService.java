package com.ai.resume_analyzer.Service;

import com.ai.resume_analyzer.dto.AnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AIService {

    // Stop words (ignore common English words)
    private static final Set<String> STOP_WORDS = Set.of(
            "the", "and", "for", "are", "with", "this", "that",
            "will", "you", "your", "our", "from", "have", "has",
            "was", "were", "be", "to", "of", "in", "on", "as",
            "we", "is", "a", "an", "or", "by", "at"
    );

    public AnalysisResponse analyze(String resumeText, String jdText) {

        // Step 1: Extract skills from JD
        Set<String> jdSkills = extractSkillsFromJD(jdText);

        // Step 2: Extract words from Resume
        Set<String> resumeWords = extractWords(resumeText);

        // Step 3: Find matched skills
        Set<String> matched = new HashSet<>();
        for (String skill : jdSkills) {
            if (resumeWords.contains(skill)) {
                matched.add(skill);
            }
        }

        // Step 4: Find missing skills
        Set<String> missing = new HashSet<>(jdSkills);
        missing.removeAll(matched);

        // Step 5: Calculate score
        int score = 0;
        if (!jdSkills.isEmpty()) {
            score = (matched.size() * 100) / jdSkills.size();
        }

        // Step 6: Prepare response
        AnalysisResponse response = new AnalysisResponse();
        response.setMatchScore(score + "%");
        response.setMatchedSkills(new ArrayList<>(matched));
        response.setMissingSkills(new ArrayList<>(missing));
        response.setSuggestions(generateSuggestions(missing));

        return response;
    }

    // 🔹 Extract skills ONLY from JD (no hardcoding)
    private Set<String> extractSkillsFromJD(String jdText) {

        Set<String> skills = new HashSet<>();

        if (jdText == null) return skills;

        String[] words = jdText.toLowerCase().split("\\W+");

        for (String word : words) {

            if (word.length() <= 2) continue;         // ignore small words
            if (STOP_WORDS.contains(word)) continue;  // ignore common words

            skills.add(word);
        }

        return skills;
    }

    // 🔹 Extract words from resume
    private Set<String> extractWords(String text) {

        if (text == null) return new HashSet<>();

        String[] words = text.toLowerCase().split("\\W+");

        return new HashSet<>(Arrays.asList(words));
    }

    // 🔹 Suggestions based on missing skills
    private List<String> generateSuggestions(Set<String> missingSkills) {

        List<String> suggestions = new ArrayList<>();

        if (!missingSkills.isEmpty()) {
            suggestions.add("Improve missing skills: " + missingSkills);
        } else {
            suggestions.add("Good match! You are suitable for this role.");
        }

        suggestions.add("Add relevant projects based on job description");

        return suggestions;
    }
}