package dev.riyenas.chatbot.crawl;

import dev.riyenas.chatbot.web.dto.common.ButtonEnum;
import dev.riyenas.chatbot.web.dto.common.Link;
import dev.riyenas.chatbot.web.dto.common.ListItem;
import dev.riyenas.chatbot.web.dto.output.ListCard;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoticeCrawlTest {
    private static Logger log = LoggerFactory.getLogger(NoticeCrawlTest.class);
    private final static String NOTICE_URL = "http://board.sejong.ac.kr/boardlist.do?bbsConfigFK=333";

    @Test
    @DisplayName("세종대학교 공지사항 크롤링")
    public void SejongUnivNoticeCrawl() throws IOException {
        Document doc = Jsoup.connect(NOTICE_URL).get();
        Elements elements = doc.select("body>div>table>tbody>tr");

        List<ListItem> listItems = new ArrayList<>();

        for(Element element : elements){
            listItems.add(
                    ListItem.of(
                            element.selectFirst(".subject").text(),
                            element.selectFirst(".writer").text(),
                            null,
                            new Link(element.selectFirst("a").attr("abs:href"))
                )
            );
        }

        ListCard listCard = ListCard.of(
                listItems.get(1),
                listItems,
                Arrays.asList(
                        ButtonEnum.MESSAGE.action("메시지", "메시지 입니다")
                )
        );


        log.info(listCard.toString());
    }
}
