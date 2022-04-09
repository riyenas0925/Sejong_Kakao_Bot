package dev.riyenas.chatbot.web;

import dev.mini.kakaoiopenbuilder.skill.payload.SkillPayload;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.service.notice.NoticeCrawlerService;
import dev.riyenas.chatbot.service.notice.NoticeService;
import dev.riyenas.chatbot.web.skill.common.QuickReplyEnum;
import dev.riyenas.chatbot.web.skillresponse.SkillResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoNoticeSkillApi {

    private final NoticeService noticeService;
    private final NoticeCrawlerService noticeCrawlerService;

    @GetMapping("notice/crawl")
    public void noticeCrawl() throws IOException {
        noticeService.saveAll(noticeCrawlerService.noticeCrawlAll());
    }

    @PostMapping("notice")
    public SkillResponseTemplate sejongNotice(@RequestBody SkillPayload payload) {
        Map<String, String> params = payload.getAction().getParams();
        String noticeTitle = params.get("sys_sejong_notice_type");

        log.info(noticeTitle + " : " + payload);

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
}
