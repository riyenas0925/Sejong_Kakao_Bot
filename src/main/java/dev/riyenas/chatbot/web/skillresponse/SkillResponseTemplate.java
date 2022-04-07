package dev.riyenas.chatbot.web.skillresponse;

import dev.riyenas.chatbot.web.skill.common.QuickReply;
import dev.riyenas.chatbot.web.skill.output.*;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Getter
@ToString
public class SkillResponseTemplate extends SkillResponse{
    private Map<String, List<Object>> template;

    public SkillResponseTemplate(){
        super();
        this.template = new HashMap<>();
        this.template.put("outputs", new ArrayList<>());
        this.template.put("quickReplies", new ArrayList<>());
    }

    public SkillResponseTemplate addSimpleText(String text){
        this.template.get("outputs").add(
                Map.of("simpleText", new SimpleText(text))
        );

        return this;
    }

    public SkillResponseTemplate addSimpleImage(String url, String altText) {
        this.template.get("outputs").add(
                Map.of("simpleImage", new SimpleImage(url, altText))
        );

        return this;
    }

    public SkillResponseTemplate addBasicCard(BasicCard basicCard){
        this.template.get("outputs").add(
                Map.of("basicCard", basicCard)
        );
        return this;
    }

    public SkillResponseTemplate addListCard(ListCard listCard){
        this.template.get("outputs").add(
                Map.of("listCard", listCard)
        );
        return this;
    }

    public SkillResponseTemplate addCarousel(Carousel carousel){
        this.template.get("outputs").add(
                Map.of("carousel", carousel)
        );
        return this;
    }

    public SkillResponseTemplate addQuickReplies(List<QuickReply> quickReplies){
        for(QuickReply quickReply : quickReplies){
            this.template.get("quickReplies").add(
                quickReply
            );
        }

        return this;
    }

    public SkillResponseTemplate addSkillResponse(SkillResponseTemplate skillResponse) {
        List<Object> outputs = skillResponse.getTemplate().get("outputs");

        this.template.get("outputs").addAll(
                outputs
        );

        return this;
    }

}
