package dev.riyenas.chatbot.web.skillpayload.intent;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class MatchedKnowledges {
    private List<String> categories;
    private String questions;
    private String answer;
    private String imageUrl;
    private String landingUrl;
}
