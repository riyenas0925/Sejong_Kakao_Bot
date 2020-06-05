package dev.riyenas.chatbot.web.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Link {
    private String web;

    @Builder
    public Link(String url) {
        this.web = url;
    }
}