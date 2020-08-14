package dev.riyenas.chatbot.domain.cafeteria;

public enum MealTimeType {
    BREAKFAST("아침"),
    LUNCH("점심"),
    DINNER("저녁"),
    ANYTIME("항상");

    private String value;

    MealTimeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
