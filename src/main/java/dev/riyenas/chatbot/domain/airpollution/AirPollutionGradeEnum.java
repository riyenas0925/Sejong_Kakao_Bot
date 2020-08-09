package dev.riyenas.chatbot.domain.airpollution;

import java.util.Arrays;

public enum AirPollutionGradeEnum {
    UNDEFINED("측정값 없음", 0L),
    GOOD("좋음", 1L),
    AVERAGE("보통", 2L),
    BAD("나쁨", 3L),
    VERY_BAD("매우 나쁨", 4L);

    private String title;
    private Long id;

    AirPollutionGradeEnum(String title, Long id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
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
