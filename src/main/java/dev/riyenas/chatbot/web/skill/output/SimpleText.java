package dev.riyenas.chatbot.web.skill.output;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SimpleText {
    private String text;

    public SimpleText(String text) {
        this.text = text;
    }
}