package dev.riyenas.chatbot.domain.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String writer;
    private Long index;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    @Column(columnDefinition = "LONGTEXT")
    private String link;

    @Column
    @Enumerated(value = EnumType.STRING)
    private NoticeTypeEnum type;

    @Builder
    public Notice(Long id, String title, String writer, Long index, Date date, NoticeTypeEnum type, String link) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.index = index;
        this.date = date;
        this.type = type;
        this.link = link;
    }
}
