package com.ai.resume_analyzer.dto;

import lombok.Data;
import java.util.List;

@Data
public class AnalysisResponse {

    private String matchScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private List<String> suggestions;
}