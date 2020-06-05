package dev.riyenas.chatbot.web.dto.skill;

import dev.riyenas.chatbot.web.dto.output.SimpleText;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class SkillResponseTemplate extends SkillResponse{
    private Map<String, List<Object>> template;

    public SkillResponseTemplate(){
        super();
        this.template = new HashMap<>();
        this.template.put("outputs", new ArrayList<>());
    }

    public SkillResponseTemplate addSimpleText(String text){
        this.template.get("outputs").add(
                new SimpleText(text)
        );
        return this;
    }
}
