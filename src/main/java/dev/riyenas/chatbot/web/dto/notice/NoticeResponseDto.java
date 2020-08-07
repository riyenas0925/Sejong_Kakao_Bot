package dev.riyenas.chatbot.web.dto.notice;

import dev.riyenas.chatbot.domain.notice.Notice;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String date;
    private String type;

    public NoticeResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.writer = entity.getWriter();
        this.date = entity.getDate().toString();
        this.type = entity.getType().getTitle();
    }
}
