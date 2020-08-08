package dev.riyenas.chatbot.airpollution;

import dev.riyenas.chatbot.service.airpollution.AirPollutionService;
import dev.riyenas.chatbot.web.dto.airpollution.AirPollutionRequestDto;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPollutionTest {
    private static Logger log = LoggerFactory.getLogger(AirPollutionTest.class);

    @Autowired
    private AirPollutionService airPollutionService;

    @Test
    @DisplayName(value = "미세먼지 API JSON 응답을 DTO에 매핑")
    public void apiTest() throws IOException, JSONException {
        //given
        String json = "{\n" +
                "  \"list\": [\n" +
                "    {\n" +
                "      \"_returnType\": \"json\",\n" +
                "      \"coGrade\": \"1\",\n" +
                "      \"coValue\": \"0.2\",\n" +
                "      \"dataTerm\": \"\",\n" +
                "      \"dataTime\": \"2020-08-08 18:00\",\n" +
                "      \"khaiGrade\": \"1\",\n" +
                "      \"khaiValue\": \"33\",\n" +
                "      \"mangName\": \"도시대기\",\n" +
                "      \"no2Grade\": \"1\",\n" +
                "      \"no2Value\": \"0.014\",\n" +
                "      \"numOfRows\": \"10\",\n" +
                "      \"o3Grade\": \"1\",\n" +
                "      \"o3Value\": \"0.020\",\n" +
                "      \"pageNo\": \"1\",\n" +
                "      \"pm10Grade\": \"1\",\n" +
                "      \"pm10Grade1h\": \"1\",\n" +
                "      \"pm10Value\": \"9\",\n" +
                "      \"pm10Value24\": \"13\",\n" +
                "      \"pm25Grade\": \"1\",\n" +
                "      \"pm25Grade1h\": \"1\",\n" +
                "      \"pm25Value\": \"7\",\n" +
                "      \"pm25Value24\": \"9\",\n" +
                "      \"resultCode\": \"\",\n" +
                "      \"resultMsg\": \"\",\n" +
                "      \"rnum\": 0,\n" +
                "      \"serviceKey\": \"\",\n" +
                "      \"sidoName\": \"\",\n" +
                "      \"so2Grade\": \"1\",\n" +
                "      \"so2Value\": \"0.002\",\n" +
                "      \"stationCode\": \"\",\n" +
                "      \"stationName\": \"\",\n" +
                "      \"totalCount\": \"\",\n" +
                "      \"ver\": \"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        //when
        AirPollutionRequestDto requestDto = airPollutionService.JsonToRequestDto(json);

        //that
        Assertions.assertEquals(requestDto.get_returnType(), "json");
        Assertions.assertEquals(requestDto.getDataTime(), "2020-08-08 18:00");
    }
}
