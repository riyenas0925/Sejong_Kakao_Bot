package dev.riyenas.chatbot.service.notice;

import dev.riyenas.chatbot.domain.notice.Notice;
import dev.riyenas.chatbot.domain.notice.NoticeRepository;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.web.skill.common.Button;
import dev.riyenas.chatbot.web.skill.common.ButtonEnum;
import dev.riyenas.chatbot.web.skill.common.Link;
import dev.riyenas.chatbot.web.skill.common.ListItem;
import dev.riyenas.chatbot.web.skill.output.ListCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    private final static String NOTICE_BASE_URL = "http://board.sejong.ac.kr/boardlist.do?bbsConfigFK=";
    private final static String SEJONG_IMG_URL = "http://www.sejong.ac.kr/sogood_sm/2018_05_30/main_vi/img4.jpg";

    @Transactional
    public Long save(Notice notice) {
        return noticeRepository.save(notice).getId();
    }

    @Transactional
    public void saveAll(Map<NoticeTypeEnum, List<Notice>> noticeMap){
        for(NoticeTypeEnum type : noticeMap.keySet()) {
            noticeRepository.saveAll(noticeMap.get(type));
        }
    }

    @Transactional(readOnly = true)
    public ListCard findByTypeDesc(NoticeTypeEnum type) {
        List<ListItem> lists = noticeRepository.findByTypeDesc(type)
                .stream()
                .map(ListItem::of)
                .collect(Collectors.toList());

        ListItem header = ListItem.builder()
                .title("세종대학교 " + type.getTitle() + " 공지사항")
                .imageUrl(SEJONG_IMG_URL)
                .link(new Link(NOTICE_BASE_URL + type.getFk()))
                .build();

        List<Button> buttons = Arrays.asList(
                ButtonEnum.WEBLINK.action("더보기", NOTICE_BASE_URL + type.getFk())
        );

        return new ListCard(header, lists, buttons);
    }
}
