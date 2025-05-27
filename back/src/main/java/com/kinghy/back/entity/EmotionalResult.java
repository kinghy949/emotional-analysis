package com.kinghy.back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "emotional_result")
public class EmotionalResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String text;

    @Column(nullable = false)
    private Integer sentiment;

    @Column(nullable = false)
    private Float confidence;

    @Column(nullable = false)
    private Float positiveProb;

    @Column(nullable = false)
    private Float negativeProb;

    // 构造方法、Getter和Setter
    public EmotionalResult() {}

    public EmotionalResult(String text, Integer sentiment, Float confidence, Float positiveProb, Float negativeProb) {
        this.text = text;
        this.sentiment = sentiment;
        this.confidence = confidence;
        this.positiveProb = positiveProb;
        this.negativeProb = negativeProb;
    }

    // Getter和Setter方法
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Integer getSentiment() { return sentiment; }
    public void setSentiment(Integer sentiment) { this.sentiment = sentiment; }
    public Float getConfidence() { return confidence; }
    public void setConfidence(Float confidence) { this.confidence = confidence; }
    public Float getPositiveProb() { return positiveProb; }
    public void setPositiveProb(Float positiveProb) { this.positiveProb = positiveProb; }
    public Float getNegativeProb() { return negativeProb; }
    public void setNegativeProb(Float negativeProb) { this.negativeProb = negativeProb; }
}