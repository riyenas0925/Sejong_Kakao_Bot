package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.web.dto.skill.SkillPayload;
import dev.riyenas.chatbot.web.dto.skill.SkillResponse;
import dev.riyenas.chatbot.web.dto.skill.SkillResponseData;
import dev.riyenas.chatbot.web.dto.skill.SkillResponseTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/test/")
public class KakaoSkillApi {

    @PostMapping("template")
    public SkillResponse testTemplateSkill(@RequestBody SkillPayload payload) {
        SkillResponse skillResponse = new SkillResponseTemplate()
                .addSimpleText("test")
                .addSimpleText("test2");

        return skillResponse;
    }

    @PostMapping("data")
    public SkillResponse testDataSkill(@RequestBody SkillPayload payload) {
        SkillResponse skillResponse = new SkillResponseData()
                .addData("key", "value");

        return skillResponse;
    }
}
