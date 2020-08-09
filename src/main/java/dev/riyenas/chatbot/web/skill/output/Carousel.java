package dev.riyenas.chatbot.web.skill.output;

import dev.riyenas.chatbot.web.dto.airpollution.AirPollutionResponseCarouselDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Carousel {
    private String type;
    private List<BasicCard> items;

    @Builder
    public Carousel(String type, List<BasicCard> items){
        this.type = type;
        this.items = items;
    }

    public static Carousel of(String type, List<BasicCard> items) {
        return new Carousel(type, items);
    }

    public static Carousel of(AirPollutionResponseCarouselDto carousel) {
        return new Carousel(carousel.getType(), carousel.getItems());
    }
}
