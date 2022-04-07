package dev.riyenas.chatbot.web.skillpayload.userrequest;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class User {
    private String id;
    private String type;
    private Properties properties;
}
