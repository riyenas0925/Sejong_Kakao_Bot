package dev.riyenas.chatbot.web.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class SimpleText {
    private Map<String, String> simpleText;

    public SimpleText(String text) {
        this.simpleText = Map.of("text", text);
    }
}