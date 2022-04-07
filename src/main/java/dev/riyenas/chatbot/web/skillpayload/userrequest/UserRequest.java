package dev.riyenas.chatbot.web.skillpayload.userrequest;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserRequest {
    private String timezone;
    private Block block;
    private String utterance;
    private String lang;
    private User user;
}
