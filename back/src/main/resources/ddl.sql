CREATE TABLE IF NOT EXISTS emotional_result (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(2048) NOT NULL,
    sentiment INT NOT NULL,
    confidence FLOAT NOT NULL,
    positive_prob FLOAT NOT NULL,
    negative_prob FLOAT NOT NULL,
    INDEX idx_sentiment (sentiment)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;