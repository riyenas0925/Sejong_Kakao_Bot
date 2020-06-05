package dev.riyenas.chatbot.web.dto.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Thumbnail {
    private String imageUrl;
    private Link link;
    private boolean fixedRatio;
    private Long width;
    private Long height;

    public Thumbnail(String imageUrl, Link link, boolean fixedRatio, Long width, Long height){
        this.imageUrl = imageUrl;
        this.link = link;
        this.fixedRatio = fixedRatio;
        this.width = width;
        this.height = height;
    }

    public Thumbnail(String imageUrl, Link link){
        this.imageUrl = imageUrl;
        this.link = link;
    }
}
