package dev.riyenas.chatbot.web.skill.common;

import dev.riyenas.chatbot.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ListItem {
    private String title;
    private String description;
    private String imageUrl;
    private Link link;

    @Builder
    public ListItem(String title, String description, String imageUrl, Link link) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.link = link;
    }

    public static ListItem of(String title, String description, String imageUrl, Link link) {
        return new ListItem(title, description, imageUrl, link);
    }

    public static ListItem of(Notice notice) {
        return new ListItem(notice.getTitle(), notice.getWriter(), null, new Link(notice.getLink()));
    }
}
