package dev.riyenas.chatbot.web.skillresponse;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@ToString
public class SkillResponse {
    private String version;

    public static final String SKILL_RESPONSE_VERSION = "2.0";

    public SkillResponse() {
        this.version = SKILL_RESPONSE_VERSION;
    }
}
