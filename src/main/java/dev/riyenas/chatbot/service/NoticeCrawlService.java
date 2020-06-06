package dev.riyenas.chatbot.service;

import dev.riyenas.chatbot.web.dto.common.ButtonEnum;
import dev.riyenas.chatbot.web.dto.common.Link;
import dev.riyenas.chatbot.web.dto.common.ListItem;
import dev.riyenas.chatbot.web.dto.output.ListCard;
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
public class NoticeCrawlService {
    private final static String NOTICE_URL = "http://board.sejong.ac.kr/boardlist.do?bbsConfigFK=333";
    private final static String SEJONG_IMG_URL = "https://img.huffingtonpost.com/asset/5d80cb9a240000d3267c75b7.jpeg?ops=1200_630";
    private final static ListItem LIST_CARD_HEADER = ListItem.builder()
            .title("세종대학교 공지사항")
            .description("세종대학교 일반공지 사항입니다.")
            .imageUrl(SEJONG_IMG_URL)
            .link(new Link(NOTICE_URL))
            .build();

    @Cacheable(value = "sejong")
    public ListCard sejongNoticeCrawl() throws IOException {
        Document doc = Jsoup.connect(NOTICE_URL).get();
        Elements elements = doc.select("body>div>table>tbody>tr");

        List<ListItem> listItems = new ArrayList<>();

        for(Element element : elements){
            String title = element.selectFirst(".subject").text();
            String writer = element.selectFirst(".writer").text();
            String url = element.selectFirst("a").attr("abs:href");

            listItems.add(
                    ListItem.builder()
                            .title(title)
                            .description(writer)
                            .imageUrl(null)
                            .link(new Link(url))
                            .build()
            );
        }

        return ListCard.of(
                LIST_CARD_HEADER,
                listItems.subList(0,5),
                Arrays.asList(
                        ButtonEnum.WEBLINK.action("더보기", NOTICE_URL)
                )
        );
    }
}
