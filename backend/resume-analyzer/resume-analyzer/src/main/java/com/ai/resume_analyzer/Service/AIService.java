package com.ai.resume_analyzer.Service;

import com.ai.resume_analyzer.dto.AnalysisResponse;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AIService {

    public AnalysisResponse analyze(String resumeText, String jdText) {
        System.out.println("=========================================");
        System.out.println("📊 Resume Analyzer - Smart Keyword Matching");
        System.out.println("=========================================");
        
        String resumeLower = resumeText.toLowerCase();
        String jdLower = jdText.toLowerCase();
        
        // Common skills database
        String[] allSkills = {
            "java", "python", "javascript", "react", "angular", "vue", "node", 
            "spring", "spring boot", "hibernate", "jpa", "rest api", "microservices",
            "docker", "kubernetes", "aws", "azure", "gcp", "mysql", "postgresql",
            "mongodb", "redis", "git", "jenkins", "ci/cd", "junit", "mockito",
            "html", "css", "typescript", "redux", "graphql", "kafka", "linux"
        };
        
        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();
        
        // Find which skills are in JD
        List<String> jdSkills = new ArrayList<>();
        for (String skill : allSkills) {
            if (jdLower.contains(skill)) {
                jdSkills.add(skill);
            }
        }
        
        // Check which JD skills are in resume
        for (String skill : jdSkills) {
            if (resumeLower.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }
        
        // Calculate match score
        int score = 0;
        if (!jdSkills.isEmpty()) {
            score = (matchedSkills.size() * 100) / jdSkills.size();
        }
        
        // Generate suggestions
        List<String> suggestions = new ArrayList<>();
        if (!missingSkills.isEmpty()) {
            suggestions.add("🔧 Add these skills: " + String.join(", ", missingSkills));
        }
        suggestions.add("📊 Add numbers to your achievements (e.g., 'Improved performance by 30%')");
        suggestions.add("🎯 Tailor your resume with keywords from the job description");
        suggestions.add("📝 Add a 'Technical Skills' section at the top of your resume");
        
        if (score >= 80) {
            suggestions.add("✅ Great match! Focus on highlighting your best achievements");
        } else if (score >= 60) {
            suggestions.add("👍 Good foundation! Add the missing skills above");
        } else {
            suggestions.add("⚠️ Your resume needs more relevant keywords from the job description");
        }
        
        AnalysisResponse response = new AnalysisResponse();
        response.setMatchScore(score + "%");
        response.setMatchedSkills(matchedSkills);
        response.setMissingSkills(missingSkills);
        response.setSuggestions(suggestions);
        
        System.out.println("📊 Match Score: " + score + "%");
        System.out.println("✅ Matched: " + matchedSkills.size() + " skills");
        System.out.println("❌ Missing: " + missingSkills.size() + " skills");
        System.out.println("=========================================");
        
        return response;
    }
}