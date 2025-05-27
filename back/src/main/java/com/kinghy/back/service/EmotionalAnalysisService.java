package com.kinghy.back.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinghy.back.entity.EmotionalResult;
import com.kinghy.back.repository.EmotionalResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmotionalAnalysisService {

    private final EmotionalResultRepository resultRepository;
    private final ObjectMapper objectMapper;

    @Value("${baidu.api.access-token}")
    private String accessToken;

    @Value("${baidu.api.sentiment-url}")
    private String sentimentUrl;

    public EmotionalAnalysisService(EmotionalResultRepository resultRepository) {
        this.resultRepository = resultRepository;
        this.objectMapper = new ObjectMapper();
    }

    public EmotionalResult analyzeText(String text) throws Exception {
        // 构造请求体（与实际接口入参一致）
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", text);
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        String responseStr = HttpRequest.post(sentimentUrl+accessToken)
                .body(jsonBody)
                .contentType("application/json")
                .execute()
                .body();

        // 解析实际响应（与用户提供的出参结构匹配）
        Map<String, Object> responseMap = JSONUtil.toBean(responseStr, Map.class);

        // 验证响应完整性（根据实际接口补充log_id检查）
        if (!responseMap.containsKey("items") || !responseMap.containsKey("log_id")) {
            throw new IllegalArgumentException("API响应格式错误，缺少items或log_id字段：" + responseStr);
        }

        // 解析items数组（处理可能的空数组情况）
        java.util.List<Map<String, Object>> items = (java.util.List<Map<String, Object>>) responseMap.get("items");
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("API响应items数组为空：" + responseStr);
        }
        Map<String, Object> item = items.get(0);

        // 解析具体情感值（与实际出参字段名完全一致）
        Integer sentiment = ((Number) item.get("sentiment")).intValue();
        Float confidence = ((Number) item.get("confidence")).floatValue();
        Float positiveProb = ((Number) item.get("positive_prob")).floatValue();
        Float negativeProb = ((Number) item.get("negative_prob")).floatValue();

        // 创建并保存结果（保持与实体类字段一致）
        EmotionalResult result = new EmotionalResult(text, sentiment, confidence, positiveProb, negativeProb);
        return resultRepository.save(result);
    }
}