package dev.riyenas.chatbot.web.dto.notice;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.riyenas.chatbot.domain.notice.Notice;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
public class NoticeRequestDto {
    private String title;
    private String writer;
    private String date;
    private String link;
    private NoticeTypeEnum type;

    @SneakyThrows
    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .writer(writer)
                .date(new SimpleDateFormat("yyyy.MM.dd").parse(date))
                .link(link)
                .type(type)
                .build();
    }

    @Builder
    public NoticeRequestDto(String title, String writer, String date, String link, NoticeTypeEnum type) {
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.link = link;
        this.type = type;
    }
}
