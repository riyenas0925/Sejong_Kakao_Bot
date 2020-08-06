package dev.riyenas.chatbot.service.notice;

import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.web.skill.common.ButtonEnum;
import dev.riyenas.chatbot.web.skill.common.Link;
import dev.riyenas.chatbot.web.skill.common.ListItem;
import dev.riyenas.chatbot.web.skill.output.ListCard;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NoticeCrawlerService {
    private final static String NOTICE_BASE_URL = "http://board.sejong.ac.kr/boardlist.do?bbsConfigFK=";
    private final static String SEJONG_IMG_URL = "https://img.huffingtonpost.com/asset/5d80cb9a240000d3267c75b7.jpeg?ops=1200_630";

    @Cacheable(value = "sejong")
    public ListCard sejongNoticeCrawler(NoticeTypeEnum noticeTypeEnum) throws IOException {

        List<ListItem> listItems = noticeCrawler(noticeTypeEnum);

        ListItem listHeader = ListItem.builder()
                .title("세종대학교 " + noticeTypeEnum.getTitle() + " 공지사항")
                .description("세종대학교 일반공지 사항입니다.")
                .imageUrl(SEJONG_IMG_URL)
                .link(new Link(NOTICE_BASE_URL + noticeTypeEnum.getFk()))
                .build();

        return ListCard.of(
                listHeader,
                listItems.subList(0,5),
                Arrays.asList(
                        ButtonEnum.WEBLINK.action("더보기", NOTICE_BASE_URL + noticeTypeEnum.getFk())
                )
        );
    }

    public List<ListItem> noticeCrawler(NoticeTypeEnum noticeTypeEnum) throws IOException {
        Document doc = Jsoup.connect(NOTICE_BASE_URL + noticeTypeEnum.getFk()).get();
        Elements elements = doc.select("body>div>table>tbody>tr");

        List<ListItem> listItems = new ArrayList<>();

        for(Element element : elements){
            String title = element.selectFirst(".subject").text();
            String writer = element.selectFirst(".writer").text();
            String url = element.selectFirst("a").attr("abs:href");
            String date = element.selectFirst(".date").text();

            listItems.add(
                    ListItem.builder()
                            .title(title)
                            .description(date + ", " + writer)
                            .imageUrl(null)
                            .link(new Link(url))
                            .build()
            );
        }

        return listItems;
    }
}
