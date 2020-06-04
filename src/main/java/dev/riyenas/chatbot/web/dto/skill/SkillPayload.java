package dev.riyenas.chatbot.web.dto.skill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class SkillPayload {
    private Map<String, Object> userRequest;
    private List<Object> contexts;
    private Map<String, Object> bot;
    private Map<String, Object> action;

    public SkillPayload(Map<String, Object> userRequest,
                        List<Object> contexts,
                        Map<String, Object> bot,
                        Map<String, Object> action) {
        this.userRequest = userRequest;
        this.contexts = contexts;
        this.bot = bot;
        this.action = action;
    }
}
