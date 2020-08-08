package dev.riyenas.chatbot.service.airpollution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.riyenas.chatbot.domain.airpollution.AirPollution;
import dev.riyenas.chatbot.web.dto.airpollution.AirPollutionRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class AirPollutionService {
    private final static String AIR_POLLUTION_API_BASE_URL = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?dataTerm=daily&pageNo=1&numOfRows=1&ServiceKey=2yxedWhsOvWYuVPZVgdJSyww2iQieiYpJmLGysjZ7z3SKWK4kggcJRlUDZv1QmJzQkQRIyjDbMHslbCVLTtZuQ%3D%3D&ver=1.3&_returnType=json&stationName=";

    public AirPollution getAirPollutionData(String location) throws JSONException, IOException {
        String json = Jsoup.connect(AIR_POLLUTION_API_BASE_URL + location)
                .ignoreContentType(true)
                .execute()
                .body();

        return JsonToRequestDto(json).toEntity();
    }

    public AirPollutionRequestDto JsonToRequestDto(String json) throws JSONException, JsonProcessingException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonArray.get(0).toString(), AirPollutionRequestDto.class);
    }
}
