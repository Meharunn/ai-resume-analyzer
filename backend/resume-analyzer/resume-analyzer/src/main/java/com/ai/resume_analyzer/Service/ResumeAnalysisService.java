package com.ai.resume_analyzer.Service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeAnalysisService {

    private final SkillExtractorService extractor;

    public ResumeAnalysisService(SkillExtractorService extractor) {
        this.extractor = extractor;
    }

    public Map<String, Object> analyze(String resumeText, String jdText) {

        Set<String> resumeSkills = extractor.extractSkills(resumeText);
        Set<String> jdSkills = extractor.extractSkills(jdText);

        Set<String> matched = new HashSet<>();
        Set<String> missing = new HashSet<>();

        // 🔥 SMART COMPARISON
        for (String jdSkill : jdSkills) {
            boolean found = false;

            for (String resSkill : resumeSkills) {
                if (extractor.isSimilar(jdSkill, resSkill)) {
                    matched.add(jdSkill);
                    found = true;
                    break;
                }
            }

            if (!found) {
                missing.add(jdSkill);
            }
        }

        int score = jdSkills.isEmpty() ? 0 :
                (matched.size() * 100) / jdSkills.size();

        Map<String, Object> result = new HashMap<>();
        result.put("score", score);
        result.put("matchedSkills", matched);
        result.put("missingSkills", missing);

        result.put("suggestions", List.of(
                "Improve missing skills: " + missing,
                "Add relevant projects based on job description"
        ));

        return result;
    }
}