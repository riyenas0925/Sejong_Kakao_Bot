package dev.riyenas.chatbot.notice;

import dev.riyenas.chatbot.domain.notice.Notice;
import dev.riyenas.chatbot.domain.notice.NoticeRepository;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import dev.riyenas.chatbot.web.dto.notice.NoticeResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeTest {
    private static Logger log = LoggerFactory.getLogger(NoticeTest.class);

    @Autowired
    private NoticeRepository noticeRepository;

    @Before
    public void before() throws ParseException {
        noticeRepository.saveAll(
                Arrays.asList(
                        Notice.builder()
                                .writer("test1")
                                .type(NoticeTypeEnum.ENTRANCE)
                                .date(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.07"))
                                .build(),
                        Notice.builder()
                                .writer("test2")
                                .type(NoticeTypeEnum.JOB)
                                .date(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.08"))
                                .build(),
                        Notice.builder()
                                .writer("test3")
                                .type(NoticeTypeEnum.ENTRANCE)
                                .date(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.09"))
                                .build(),
                        Notice.builder()
                                .writer("test4")
                                .type(NoticeTypeEnum.RECRUITMENT)
                                .date(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.10"))
                                .build(),
                        Notice.builder()
                                .writer("test5")
                                .type(NoticeTypeEnum.JOB)
                                .date(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.11"))
                                .build(),
                        Notice.builder()
                                .writer("test6")
                                .type(NoticeTypeEnum.RECRUITMENT)
                                .date(new SimpleDateFormat("yyyy.MM.dd").parse("2020.08.12"))
                                .build()
                )
        );
    }

    @Test
    public void findAllDesc() {
        log.info(noticeRepository.findAllDesc().stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList()).toString());
    }

    @Test
    public void findByTypeDesc() {
        log.info(noticeRepository.findByTypeDesc(NoticeTypeEnum.JOB).stream()
                .map(NoticeResponseDto::new)
                .collect(Collectors.toList()).toString());
    }
}
