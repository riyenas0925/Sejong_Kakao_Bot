package dev.riyenas.chatbot.web.skillpayload.intent;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Intent {
    private String id;
    private String name;
    private Extra extra;
}
