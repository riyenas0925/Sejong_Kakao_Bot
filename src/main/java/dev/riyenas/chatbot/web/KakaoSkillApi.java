package dev.riyenas.chatbot.web;

import dev.riyenas.chatbot.web.dto.common.ButtonEnum;
import dev.riyenas.chatbot.web.dto.common.Link;
import dev.riyenas.chatbot.web.dto.common.ListItem;
import dev.riyenas.chatbot.web.dto.common.Thumbnail;
import dev.riyenas.chatbot.web.dto.output.BasicCard;
import dev.riyenas.chatbot.web.dto.output.Carousel;
import dev.riyenas.chatbot.web.dto.output.CarouselEnum;
import dev.riyenas.chatbot.web.dto.output.ListCard;
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

    private final static String IMAGE_URL = "https://img.huffingtonpost.com/asset/5d80cb9a240000d3267c75b7.jpeg?ops=1200_630";
    private final static String HOMEPAGE_URL = "https://blog.riyenas.dev";

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
                                ButtonEnum.PHONE.action("전화 걸기", "description", "010-1234-5678"),
                                ButtonEnum.WEBLINK.action("이미지 보기", "description", IMAGE_URL)
                        )
                ))
                .addListCard(ListCard.of(
                        ListItem.of(
                                "Header",
                                "description",
                                HOMEPAGE_URL,
                                new Link(HOMEPAGE_URL)
                        ),
                        Arrays.asList(
                                ListItem.of(
                                        "List 1",
                                        "description",
                                        HOMEPAGE_URL,
                                        new Link(HOMEPAGE_URL)
                                ),
                                ListItem.of(
                                        "List 1",
                                        "description",
                                        HOMEPAGE_URL,
                                        new Link(HOMEPAGE_URL)
                                )
                        ),
                        Arrays.asList(ButtonEnum.WEBLINK.action("홈페이지", "description", HOMEPAGE_URL))
                ))
                .addCarousel(Carousel.of(
                        CarouselEnum.BASIC_CARD.getValue(),
                        Arrays.asList(
                                BasicCard.of(
                                        "Carousel 1",
                                        "Description",
                                        new Thumbnail(IMAGE_URL, new Link(IMAGE_URL)),
                                        Arrays.asList(
                                                ButtonEnum.MESSAGE.action("메시지", "description", "메시지 입니다")
                                        )
                                ),
                                BasicCard.of(
                                        "Carousel 2",
                                        "Description",
                                        new Thumbnail(IMAGE_URL, new Link(IMAGE_URL)),
                                        Arrays.asList(
                                                ButtonEnum.MESSAGE.action("메시지", "description", "메시지 입니다")
                                        )
                                )
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
