package dev.riyenas.chatbot.domain.airpollution;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AirPollution {
    private Long pm10Value;                         //미세먼지(PM10) 농도
    private Long pm10Value24;                       //미세먼지(PM10) 24시간 예측 이동 농도
    private AirPollutionGradeEnum pm10Grade;        //미세먼지(PM10) 24시간 등급
    private AirPollutionGradeEnum pm10Grade1h;      //미세먼지(PM10) 1시간 등급

    private Long pm25Value;                         //미세먼지(PM25) 농도
    private Long pm25Value24;                       //미세먼지(PM25) 24시간 예측 이동 농도
    private AirPollutionGradeEnum pm25Grade;        //미세먼지(PM25) 24시간 등급
    private AirPollutionGradeEnum pm25Grade1h;      //미세먼지(PM25) 1시간 등급

    private Long khaiValue;                         //통합대기환경수치
    private AirPollutionGradeEnum khaiGrade;        //통합대기환경등급

    @Builder
    public AirPollution(Long pm10Value, Long pm10Value24, AirPollutionGradeEnum pm10Grade,
                        AirPollutionGradeEnum pm10Grade1h, Long pm25Value, Long pm25Value24,
                        AirPollutionGradeEnum pm25Grade, AirPollutionGradeEnum pm25Grade1h,
                        Long khaiValue, AirPollutionGradeEnum khaiGrade) {
        this.pm10Value = pm10Value;
        this.pm10Value24 = pm10Value24;
        this.pm10Grade = pm10Grade;
        this.pm10Grade1h = pm10Grade1h;
        this.pm25Value = pm25Value;
        this.pm25Value24 = pm25Value24;
        this.pm25Grade = pm25Grade;
        this.pm25Grade1h = pm25Grade1h;
        this.khaiValue = khaiValue;
        this.khaiGrade = khaiGrade;
    }
}
