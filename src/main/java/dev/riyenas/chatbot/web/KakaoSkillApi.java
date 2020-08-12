package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.domain.restaurant.Menu;
import dev.riyenas.chatbot.service.airpollution.AirPollutionService;
import dev.riyenas.chatbot.service.notice.NoticeCrawlerService;
import dev.riyenas.chatbot.service.notice.NoticeService;
import dev.riyenas.chatbot.service.restaurant.RestaurantCrawlerService;
import dev.riyenas.chatbot.service.restaurant.RestaurantService;
import dev.riyenas.chatbot.web.dto.airpollution.AirPollutionResponseCarouselDto;
import dev.riyenas.chatbot.web.payload.SkillPayload;
import dev.riyenas.chatbot.web.payload.SkillResponse;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import dev.riyenas.chatbot.web.skill.common.QuickReplyEnum;
import dev.riyenas.chatbot.web.skill.output.Carousel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoSkillApi {

    private final NoticeService noticeService;
    private final NoticeCrawlerService noticeCrawlerService;
    private final AirPollutionService airPollutionService;
    private final RestaurantCrawlerService restaurantCrawlerService;
    private final RestaurantService restaurantService;

    @GetMapping("notice/crawl")
    public void test() throws IOException {
        noticeService.saveAll(noticeCrawlerService.noticeCrawlAll());
    }

    @PostMapping("notice")
    public SkillResponse sejongNotice(@RequestBody SkillPayload payload) {

        Map<String, String> params = (Map<String, String>) payload.getAction().get("params");
        String noticeTitle = params.get("sys_sejong_notice_type");

        log.info(noticeTitle + " : " + payload.toString());

        return new SkillResponseTemplate()
                .addSimpleText("세종대학교 " + noticeTitle + " 공지사항 입니다.")
                .addListCard(noticeService.findByTypeDesc(NoticeTypeEnum.findByTitle(noticeTitle)))
                .addQuickReplies(
                        Arrays.asList(
                                QuickReplyEnum.MESSAGE.action("일반", "일반 공지사항 알려줘"),
                                QuickReplyEnum.MESSAGE.action("입학", "입학 공지사항 알려줘"),
                                QuickReplyEnum.MESSAGE.action("학사", "학사 공지사항 알려줘"),
                                //QuickReplyEnum.MESSAGE.action("국제교류", "국제교류 공지사항 알려줘"),
                                QuickReplyEnum.MESSAGE.action("취업", "취업 공지사항 알려줘"),
                                QuickReplyEnum.MESSAGE.action("장학", "장학 공지사항 알려줘"),
                                QuickReplyEnum.MESSAGE.action("교내모집", "교내모집 공지사항 알려줘")
                        )
                );
    }

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

    @GetMapping("restaurant/crawl")
    public void restaurantCrawl() throws IOException {
        restaurantCrawlerService.restaurantCrawler();
    }

    @PostMapping("restaurant")
    public SkillResponse restaurant(@RequestBody SkillPayload payload) {
        Map<String, String> params = (Map<String, String>) payload.getAction().get("params");
        String restaurant = params.get("sys_restaurant_type");

        log.info("식당(" + restaurant + ") : " + payload.toString());

        List<Menu> menus = restaurantService.findAll();

        return new SkillResponseTemplate()
                .addSimpleText(menus.toString());
    }
}
