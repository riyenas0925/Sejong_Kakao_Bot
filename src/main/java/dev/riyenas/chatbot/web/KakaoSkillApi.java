package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.web.payload.SkillPayload;
import dev.riyenas.chatbot.service.notice.NoticeCrawlerService;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.web.skill.common.QuickReplyEnum;
import dev.riyenas.chatbot.web.payload.SkillResponse;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejong/")
public class KakaoSkillApi {

    private final NoticeCrawlerService noticeCrawlerService;

    @PostMapping("notice")
    public SkillResponse sejongNotice(@RequestBody SkillPayload payload) throws IOException {

        Map<String, String> params = (Map<String, String>) payload.getAction().get("params");
        String noticeTitle = params.get("sys_sejong_notice_type");

        log.info(noticeTitle + " : " + payload.toString());

        return new SkillResponseTemplate()
                .addSimpleText("세종대학교 " + noticeTitle + " 공지사항 입니다.")
                .addListCard(noticeCrawlerService.sejongNoticeCrawler(NoticeTypeEnum.findByTitle(noticeTitle)))
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
