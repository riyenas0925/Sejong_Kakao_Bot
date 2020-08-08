package dev.riyenas.chatbot.web.dto.airpollution;

import dev.riyenas.chatbot.domain.airpollution.AirPollution;
import dev.riyenas.chatbot.domain.airpollution.AirPollutionGradeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class AirPollutionRequestDto {   private String _returnType;
    private String coGrade;
    private String coValue;
    private String dataTerm;
    private String dataTime;
    private String khaiGrade;
    private String khaiValue;
    private String mangName;
    private String no2Grade;
    private String no2Value;
    private String numOfRows;
    private String o3Grade;
    private String o3Value;
    private String pageNo;
    private String pm10Grade;
    private String pm10Grade1h;
    private String pm10Value;
    private String pm10Value24;
    private String pm25Grade;
    private String pm25Grade1h;
    private String pm25Value;
    private String pm25Value24;
    private String resultCode;
    private String resultMsg;
    private String rnum;
    private String serviceKey;
    private String sidoName;
    private String so2Grade;
    private String so2Value;
    private String stationCode;
    private String stationName;
    private String totalCount;
    private String ver;

    @Builder
    public AirPollutionRequestDto(String _returnType, String coGrade, String coValue, String dataTerm, String dataTime, String khaiGrade, String khaiValue, String mangName, String no2Grade, String no2Value, String numOfRows, String o3Grade, String o3Value, String pageNo, String pm10Grade, String pm10Grade1h, String pm10Value, String pm10Value24, String pm25Grade, String pm25Grade1h, String pm25Value, String pm25Value24, String resultCode, String resultMsg, String rnum, String serviceKey, String sidoName, String so2Grade, String so2Value, String stationCode, String stationName, String totalCount, String ver) {
        this._returnType = _returnType;
        this.coGrade = coGrade;
        this.coValue = coValue;
        this.dataTerm = dataTerm;
        this.dataTime = dataTime;
        this.khaiGrade = khaiGrade;
        this.khaiValue = khaiValue;
        this.mangName = mangName;
        this.no2Grade = no2Grade;
        this.no2Value = no2Value;
        this.numOfRows = numOfRows;
        this.o3Grade = o3Grade;
        this.o3Value = o3Value;
        this.pageNo = pageNo;
        this.pm10Grade = pm10Grade;
        this.pm10Grade1h = pm10Grade1h;
        this.pm10Value = pm10Value;
        this.pm10Value24 = pm10Value24;
        this.pm25Grade = pm25Grade;
        this.pm25Grade1h = pm25Grade1h;
        this.pm25Value = pm25Value;
        this.pm25Value24 = pm25Value24;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.rnum = rnum;
        this.serviceKey = serviceKey;
        this.sidoName = sidoName;
        this.so2Grade = so2Grade;
        this.so2Value = so2Value;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.totalCount = totalCount;
        this.ver = ver;
    }

    public AirPollution toEntity() {
        return AirPollution.builder()
                .pm10Value(Long.valueOf(pm10Value))
                .pm10Value24(Long.valueOf(pm10Value24))
                .pm10Grade(AirPollutionGradeEnum.of(Long.valueOf(pm10Grade)))
                .pm10Grade1h(AirPollutionGradeEnum.of(Long.valueOf(pm10Grade1h)))
                .pm25Value(Long.valueOf(pm25Value))
                .pm25Value24(Long.valueOf(pm25Value24))
                .pm25Grade(AirPollutionGradeEnum.of(Long.valueOf(pm25Grade)))
                .pm25Grade1h(AirPollutionGradeEnum.of(Long.valueOf(pm25Grade1h)))
                .khaiValue(Long.valueOf(khaiValue))
                .khaiGrade(AirPollutionGradeEnum.of(Long.valueOf(khaiGrade)))
                .build();
    }
}
