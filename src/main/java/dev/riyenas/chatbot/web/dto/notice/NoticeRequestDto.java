package dev.riyenas.chatbot.web.dto.notice;

import dev.riyenas.chatbot.domain.notice.Notice;
import dev.riyenas.chatbot.domain.notice.NoticeTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

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
