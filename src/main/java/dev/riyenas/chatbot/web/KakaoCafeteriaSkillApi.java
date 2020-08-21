package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import dev.riyenas.chatbot.domain.menu.cafeteria.CafeteriaSkillResponse;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaCrawlerService;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaService;
import dev.riyenas.chatbot.web.payload.SkillPayload;
import dev.riyenas.chatbot.web.payload.SkillResponse;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import dev.riyenas.chatbot.web.skill.common.QuickReplyEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoCafeteriaSkillApi {

    private final CafeteriaCrawlerService cafeteriaCrawlerService;
    private final CafeteriaService cafeteriaService;

    @GetMapping("cafeteria/crawl")
    public void cafeteriaCrawl() throws IOException {
        cafeteriaCrawlerService.cafeteriaCrawler();
    }

    @PostMapping("cafeteria")
    public SkillResponse cafeteria(@RequestBody SkillPayload payload) {
        Map<String, String> params = (Map<String, String>) payload.getAction().get("params");
        String cafeteria = params.get("sys_cafeteria_type");

        log.info("식당(" + cafeteria + ") : " + payload.toString());

        Cafeteria type = Cafeteria.findBytitle(cafeteria);

        List<Menu> menus = cafeteriaService.findByCafeteriaType(type);

        return new SkillResponseTemplate()
                .addSimpleText(type.getTitle() + " 메뉴 입니다")
                .addSkillResponse(CafeteriaSkillResponse.findByCafeteriaType(type).response(menus))
                .addQuickReplies(
                        Arrays.asList(
                                QuickReplyEnum.MESSAGE.action("학생회관", "오늘 학생회관 메뉴 알려줘"),
                                //QuickReplyEnum.MESSAGE.action("우정당", "우정당 학식 알려줘"),
                                QuickReplyEnum.MESSAGE.action("군자관", "오늘 군자관 메뉴 알려줘"),
                                QuickReplyEnum.MESSAGE.action("가든뷰", "오늘 가든뷰 메뉴 알려줘")
                        )
                );
    }
}
