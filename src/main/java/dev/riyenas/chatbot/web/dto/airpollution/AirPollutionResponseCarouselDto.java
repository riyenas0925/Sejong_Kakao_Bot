package dev.riyenas.chatbot.web.dto.airpollution;

import dev.riyenas.chatbot.domain.airpollution.AirPollution;
import dev.riyenas.chatbot.web.skill.common.ButtonEnum;
import dev.riyenas.chatbot.web.skill.common.Thumbnail;
import dev.riyenas.chatbot.web.skill.output.BasicCard;
import dev.riyenas.chatbot.web.skill.output.CarouselEnum;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString
public class AirPollutionResponseCarouselDto {
    private final static String IMAGE_URL = "https://blog.toss.im/wp-content/uploads/2019/04/toss_ondemand_insurance_01.png";

    private String type;
    private List<BasicCard> items;

    public AirPollutionResponseCarouselDto(AirPollution airPollution) {
        this.type = CarouselEnum.BASIC_CARD.getValue();
        this.items = Arrays.asList(
                BasicCard.of(airPollution.getPm25Grade().getMessage(),
                        new StringBuilder()
                                .append("❇ PM10 : ")
                                .append(airPollution.getPm10Value())
                                .append(" (")
                                .append(airPollution.getPm10Grade().getTitle())
                                .append(")\n")
                                .append("✳ PM2.5 : ")
                                .append(airPollution.getPm25Value())
                                .append(" (")
                                .append(airPollution.getPm25Grade().getTitle())
                                .append(")")
                                .toString(),
                        new Thumbnail(IMAGE_URL, null),
                        Arrays.asList(
                                ButtonEnum.WEBLINK.action("자세히 보기", "")
                        )
                ),
                BasicCard.of("상세 현황",
                        new StringBuilder()
                                .append("❇ 미세먼지 (PM10)")
                                .append("\n농도 : ")
                                .append(airPollution.getPm10Value())
                                .append("㎍/㎥\n1시간 등급 : ")
                                .append(airPollution.getPm10Grade1h().getTitle())
                                .append("\n\n✳ 초미세먼지 (PM2.5)")
                                .append("\n농도 : ")
                                .append(airPollution.getPm25Value())
                                .append("㎍/㎥\n1시간 등급 : ")
                                .append(airPollution.getPm25Grade1h().getTitle())
                                .append("\n\n☁ 통합대기환경")
                                .append("\n수치 : ")
                                .append(airPollution.getKhaiValue())
                                .append("\n등급 : ")
                                .append(airPollution.getKhaiGrade().getTitle())
                                .toString(),
                        null
                )
        );
    }
}
