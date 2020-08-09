package dev.riyenas.chatbot.domain.airpollution;

import java.util.Arrays;

public enum AirPollutionGradeEnum {
    UNDEFINED("측정값 없음", 0L, "측정값이 없습니다."),
    GOOD("좋음", 1L, "실외활동 아주 좋아요"),
    AVERAGE("보통", 2L, "실외활동 괜찮아요"),
    BAD("나쁨", 3L, "실외활동 하지 않아요"),
    VERY_BAD("매우 나쁨", 4L, "실외활동 안돼요");

    private String title;
    private Long id;
    private String message;

    AirPollutionGradeEnum(String title, Long id, String message) {
        this.title = title;
        this.id = id;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public static AirPollutionGradeEnum of(Long id) {
        return Arrays.stream(values())
                .filter(v -> id.equals(v.id))
                .findFirst()
                .orElse(AirPollutionGradeEnum.UNDEFINED);
    }

    public static AirPollutionGradeEnum of (String id) {
        return Arrays.stream(values())
                .filter(v -> AirPollution.stringToLong(id).equals(v.id))
                .findFirst()
                .orElse(AirPollutionGradeEnum.UNDEFINED);
    }
}
