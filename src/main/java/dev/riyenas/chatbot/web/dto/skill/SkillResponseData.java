package dev.riyenas.chatbot.web.dto.skill;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class SkillResponseData extends SkillResponse{
    private Map<String, String> data;

    public SkillResponseData(){
        super();
        this.data = new HashMap<>();
    }

    public SkillResponseData addData(String key, String value){
        this.data.put(key, value);
        return this;
    }
}
