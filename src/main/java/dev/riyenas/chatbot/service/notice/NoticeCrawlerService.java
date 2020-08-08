package dev.riyenas.chatbot.service.notice;

import dev.riyenas.chatbot.domain.notice.Notice;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.web.dto.notice.NoticeRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class NoticeCrawlerService {
    private final static String NOTICE_BASE_URL = "http://board.sejong.ac.kr/boardlist.do?bbsConfigFK=";

    public Map<NoticeTypeEnum, List<Notice>> noticeCrawlAll() throws IOException {

        Map<NoticeTypeEnum, List<Notice>> noticeMap = new HashMap<>();

        for(NoticeTypeEnum type : NoticeTypeEnum.values()) {
            List<Notice> notices = noticeCrawler(type);
            noticeMap.put(type, notices);
        }

        return noticeMap;
    }

    public List<Notice> noticeCrawler(NoticeTypeEnum noticeType) throws IOException {
        Document doc = Jsoup.connect(NOTICE_BASE_URL + noticeType.getFk()).get();
        Elements elements = doc.select("body>div>table>tbody>tr");

        List<NoticeRequestDto> notices = new ArrayList<>();

        for(Element element : elements){
            String title = element.selectFirst(".subject").text();
            String writer = element.selectFirst(".writer").text();
            String url = element.selectFirst("a").attr("abs:href")
                    .replace("boardview", "mboardviewmobile");
            String date = element.selectFirst(".date").text();

            notices.add(
                    NoticeRequestDto.builder()
                            .title(title)
                            .date(date)
                            .link(url)
                            .writer(writer)
                            .type(noticeType)
                            .build()
            );
        }

        return notices.stream()
                .map(NoticeRequestDto::toEntity)
                .collect(Collectors.toList());
    }
}
