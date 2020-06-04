package dev.riyenas.chatbot.web.dto.skill;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class SkillResponseTemplate extends SkillResponse{
    private Map<String, List<Object>> template;

    public SkillResponseTemplate(){
        super();
        this.template = new HashMap<>();
        this.template.put("outputs", new ArrayList<>());
    }
}
