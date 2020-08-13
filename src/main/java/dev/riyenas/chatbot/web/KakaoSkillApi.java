package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.domain.cafeteria.CafeteriaTypeEnum;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.service.airpollution.AirPollutionService;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaCrawlerService;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaService;
import dev.riyenas.chatbot.service.notice.NoticeCrawlerService;
import dev.riyenas.chatbot.service.notice.NoticeService;
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
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoSkillApi {

    private final NoticeService noticeService;
    private final NoticeCrawlerService noticeCrawlerService;
    private final AirPollutionService airPollutionService;
    private final CafeteriaCrawlerService cafeteriaCrawlerService;
    private final CafeteriaService cafeteriaService;

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

    @GetMapping("cafeteria/crawl")
    public void cafeteriaCrawl() throws IOException {
        cafeteriaCrawlerService.cafeteriaCrawler();
    }

    @PostMapping("cafeteria")
    public SkillResponse cafeteria(@RequestBody SkillPayload payload) {
        Map<String, String> params = (Map<String, String>) payload.getAction().get("params");
        String cafeteria = params.get("sys_cafeteria_type");

        log.info("식당(" + cafeteria + ") : " + payload.toString());

        return cafeteriaService.findByCafeteriaType(CafeteriaTypeEnum.findBytitle(cafeteria))
                .addQuickReplies(
                        Arrays.asList(
                                QuickReplyEnum.MESSAGE.action("학생회관", "학생회관 학식 알려줘"),
                                //QuickReplyEnum.MESSAGE.action("우정당", "우정당 학식 알려줘"),
                                QuickReplyEnum.MESSAGE.action("군자관", "군자관 학식 알려줘"),
                                QuickReplyEnum.MESSAGE.action("가든뷰", "가든뷰 학식 알려줘")
                        )
                );
    }
}
