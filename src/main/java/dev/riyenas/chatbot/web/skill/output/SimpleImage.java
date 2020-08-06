package dev.riyenas.chatbot.web.skill.output;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SimpleImage {
    private String imageUrl;
    private String altText;

    public SimpleImage(String imageUrl, String altText) {
        this.imageUrl = imageUrl;
        this.altText = altText;
    }
}
