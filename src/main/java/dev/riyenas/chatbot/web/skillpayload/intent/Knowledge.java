package dev.riyenas.chatbot.web.skillpayload.intent;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Knowledge {
    private String responseType;
    private List<MatchedKnowledges> matchedKnowledges;
}
