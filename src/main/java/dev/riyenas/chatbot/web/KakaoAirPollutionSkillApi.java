package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.service.airpollution.AirPollutionService;
import dev.riyenas.chatbot.web.dto.airpollution.AirPollutionResponseCarouselDto;
import dev.riyenas.chatbot.web.payload.SkillPayload;
import dev.riyenas.chatbot.web.payload.SkillResponse;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import dev.riyenas.chatbot.web.skill.output.Carousel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoAirPollutionSkillApi {

    private final AirPollutionService airPollutionService;

    @PostMapping("airPollution")
    public SkillResponse airPollution(@RequestBody SkillPayload payload) throws IOException, JSONException {

        Map<String, String> params = (Map<String, String>) payload.getAction().get("params");
        String location = params.get("sys_air_station_location");

        log.info("미세먼지(" + location + ") : " + payload.toString());

        AirPollutionResponseCarouselDto responseCarousel = new AirPollutionResponseCarouselDto(airPollutionService.getAirPollutionData(location));

        return new SkillResponseTemplate()
                .addSimpleText(location + " 미세먼지 현황")
                .addCarousel(Carousel.of(responseCarousel));
    }
}
