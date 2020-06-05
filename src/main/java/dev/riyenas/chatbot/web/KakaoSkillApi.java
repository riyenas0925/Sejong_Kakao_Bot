package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.web.dto.common.ButtonEnum;
import dev.riyenas.chatbot.web.dto.common.Link;
import dev.riyenas.chatbot.web.dto.common.Thumbnail;
import dev.riyenas.chatbot.web.dto.output.BasicCard;
import dev.riyenas.chatbot.web.dto.skill.SkillPayload;
import dev.riyenas.chatbot.web.dto.skill.SkillResponse;
import dev.riyenas.chatbot.web.dto.skill.SkillResponseData;
import dev.riyenas.chatbot.web.dto.skill.SkillResponseTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/api/v1/test/")
public class KakaoSkillApi {

    private final static String IMAGE_URL = "https://user-images.githubusercontent.com/32615702/81304219-bc40ce00-90b7-11ea-9a02-eda93ee3600b.jpg";

    @PostMapping("template")
    public SkillResponse testTemplateSkill(@RequestBody SkillPayload payload) {

        SkillResponse skillResponse = new SkillResponseTemplate()
                .addSimpleText("test")
                .addSimpleImage(IMAGE_URL,"altText")
                .addBasicCard(BasicCard.of(
                        "Basic Card",
                        "Description",
                        new Thumbnail(IMAGE_URL, new Link(IMAGE_URL)),
                        Arrays.asList(
                                ButtonEnum.MESSAGE.action("메시지", "description", "메시지 입니다"),
                                ButtonEnum.PHONE.action("전화 걸기", "description", "010-5668-6188"),
                                ButtonEnum.WEBLINK.action("이미지 보기", "description", IMAGE_URL)
                        )
                ));

        return skillResponse;
    }

    @PostMapping("data")
    public SkillResponse testDataSkill(@RequestBody SkillPayload payload) {
        SkillResponse skillResponse = new SkillResponseData()
                .addData("key", "value");

        return skillResponse;
    }
}
