package dev.riyenas.chatbot.web.skillpayload;

import dev.riyenas.chatbot.web.skillpayload.action.Action;
import dev.riyenas.chatbot.web.skillpayload.bot.Bot;
import dev.riyenas.chatbot.web.skillpayload.intent.Intent;
import dev.riyenas.chatbot.web.skillpayload.userrequest.UserRequest;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SkillPayload {
    private Bot bot;
    private Intent intent;
    private Action action;
    private UserRequest userRequest;
}
