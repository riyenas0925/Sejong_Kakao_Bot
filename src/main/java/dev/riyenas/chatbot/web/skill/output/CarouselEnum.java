package dev.riyenas.chatbot.web.skill.output;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CarouselEnum {
    BASIC_CARD("basicCard"),
    COMMERCE_CARD("commerceCard");

    private String value;

    CarouselEnum(String value){
        this.value = value;
    }

}
