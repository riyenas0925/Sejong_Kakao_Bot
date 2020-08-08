package dev.riyenas.chatbot.web.dto.airpollution;

import dev.riyenas.chatbot.domain.airpollution.AirPollution;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AirPollutionResponseDto {
    private String message;

    public AirPollutionResponseDto(AirPollution airPollution) {
        this.message =
                "미세먼지 (pm10)" + "\n" +
                "농도 : " + airPollution.getPm10Value() + "\n" +
                "24시간 예측 이동 농도 : " + airPollution.getPm10Value24() + "\n" +
                "24시간 등급 : " + airPollution.getPm10Grade().getTitle() + "\n" +
                "1시간 등급 : " + airPollution.getPm10Grade1h().getTitle() + "\n\n" +

                "미세먼지 (pm25)" + "\n" +
                "농도 : " + airPollution.getPm25Value() + "\n" +
                "24시간 예측 이동 농도 : " + airPollution.getPm25Value24() + "\n" +
                "24시간 등급 : " + airPollution.getPm25Grade().getTitle() + "\n" +
                "1시간 등급 : " + airPollution.getPm25Grade1h().getTitle() + "\n\n" +

                "통합대기환경" + "\n" +
                "수치 : " + airPollution.getKhaiValue() + "\n" +
                "등급 : " +  airPollution.getKhaiGrade().getTitle();
    }

}
