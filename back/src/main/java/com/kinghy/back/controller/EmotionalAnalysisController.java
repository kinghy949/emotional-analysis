package com.kinghy.back.controller;

import com.kinghy.back.dto.TextRequest;
import com.kinghy.back.entity.EmotionalResult;
import com.kinghy.back.service.EmotionalAnalysisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emotional")
public class EmotionalAnalysisController {

    private final EmotionalAnalysisService analysisService;

    public EmotionalAnalysisController(EmotionalAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/analyze")
    public EmotionalResult analyze(@RequestBody TextRequest request) throws Exception {
        return analysisService.analyzeText(request.getText());
    }
}