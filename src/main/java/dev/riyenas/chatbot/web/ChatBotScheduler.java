package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.service.cafeteria.CafeteriaCrawlerService;
import dev.riyenas.chatbot.service.notice.NoticeCrawlerService;
import dev.riyenas.chatbot.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Log4j2
@Controller
@EnableAsync
@RequiredArgsConstructor
public class ChatBotScheduler {

    private final NoticeService noticeService;
    private final NoticeCrawlerService noticeCrawlerService;
    private final CafeteriaCrawlerService cafeteriaCrawlerService;

    // 30분 마다 크롤링
    @Async
    @Scheduled(cron = "0 0/30 * * * *")
    public void noticeScheduler() throws IOException {
        log.info("공지사항 크롤링중 ...");
        noticeService.saveAll(noticeCrawlerService.noticeCrawlAll());
    }

    // 6시간 마다 크롤링
    @Async
    @Scheduled(cron = "0 0 0/3 * * *")
    public void cafeteriaCrawler() throws IOException {
        log.info("학생식당 크롤링중 ...");
        cafeteriaCrawlerService.cafeteriaCrawler();
    }
}
