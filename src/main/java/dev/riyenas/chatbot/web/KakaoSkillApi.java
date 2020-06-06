package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.service.NoticeCrawlService;
import dev.riyenas.chatbot.web.dto.skill.SkillResponse;
import dev.riyenas.chatbot.web.dto.skill.SkillResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoSkillApi {

    private final NoticeCrawlService noticeCrawlService;

    @PostMapping("notice")
    public SkillResponse sejongNotice() throws IOException {
        return new SkillResponseTemplate().addListCard(
                noticeCrawlService.sejongNoticeCrawl()
        );
    }
}
