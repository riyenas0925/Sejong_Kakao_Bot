package dev.riyenas.chatbot.web.dto.common;

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
}