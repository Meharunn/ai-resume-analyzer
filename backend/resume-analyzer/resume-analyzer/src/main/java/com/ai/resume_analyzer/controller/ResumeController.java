//package com.ai.resume_analyzer.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.ai.resume_analyzer.Entity.Analysis;
//import com.ai.resume_analyzer.Entity.User;
//import com.ai.resume_analyzer.Service.AIService;
//import com.ai.resume_analyzer.Service.ResumeService;
//import com.ai.resume_analyzer.dto.AnalysisResponse;
//import com.ai.resume_analyzer.dto.ApiResponseWithData;
//import com.ai.resume_analyzer.repository.AnalysisRepository;
//import com.ai.resume_analyzer.repository.UserRepository;
//
//
//@RestController
//@RequestMapping("/resume")
//public class ResumeController {
//
//    @Autowired
//    private ResumeService resumeService;
//
//    @Autowired
//    private AnalysisRepository analysisRepository;
//    
//    @Autowired
//    private AIService aiService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/upload")
//    public ApiResponseWithData<AnalysisResponse> uploadResume(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("jd") String jd,
//            @RequestParam("userId") Long userId
//    ) throws Exception {
//
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (user == null) {
//            return new ApiResponseWithData<>(false, null, "User not found");
//        }
//
//        String resumeText = resumeService.extractText(file);
//
//        AnalysisResponse aiResponse = aiService.analyze(resumeText, jd);
//
//        Analysis analysis = new Analysis();
//        analysis.setResumeName(file.getOriginalFilename());
//        analysis.setResumeText(resumeText);
//        analysis.setJdText(jd);
//        analysis.setResult(aiResponse.toString());
//        analysis.setCreatedAt(java.time.LocalDateTime.now());
//        analysis.setUser(user);
//
//        analysisRepository.save(analysis);
//
//        return new ApiResponseWithData<>(true, aiResponse, "Resume analyzed successfully");
//    }
//    
//}

package com.ai.resume_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ai.resume_analyzer.Service.AIService;
import com.ai.resume_analyzer.Service.ResumeService;
import com.ai.resume_analyzer.dto.AnalysisResponse;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "http://localhost:3000")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private AIService aiService;

    @PostMapping("/upload")
    public AnalysisResponse uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jd") String jd
    ) throws Exception {
        // Extract text from PDF
        String resumeText = resumeService.extractText(file);
        
        // Get REAL AI analysis from Gemini
        AnalysisResponse response = aiService.analyze(resumeText, jd);
        
        return response;
    }
}