package dev.riyenas.chatbot.web.skill.output;

import dev.riyenas.chatbot.web.dto.airpollution.AirPollutionResponseDto;
import dev.riyenas.chatbot.web.skill.common.Button;
import dev.riyenas.chatbot.web.skill.common.Thumbnail;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class BasicCard {
    private String title;
    private String description;
    private Thumbnail thumbnail;
    //private Profile profile;  - 현재 지원 X
    //private Social social;    - 현재 지원 X
    private List<Button> buttons;

    public BasicCard(BasicCard basicCard) {
        this.title = basicCard.getTitle();
        this.description = basicCard.getDescription();
        this.thumbnail = basicCard.getThumbnail();
        this.buttons = basicCard.getButtons();
    }

    public BasicCard(String title, String description, Thumbnail thumbnail, List<Button> buttons) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.buttons = buttons;
    }

    public static BasicCard of(String title, String description, Thumbnail thumbnail, List<Button> buttons) {
        return new BasicCard(title, description, thumbnail, buttons);
    }

    public static BasicCard of(String title, String description, Thumbnail thumbnail) {
        return new BasicCard(title, description, thumbnail, null);
    }

    public static BasicCard of(AirPollutionResponseDto dto) {
        return new BasicCard(dto.getTitle(), dto.getDescription(), dto.getThumbnail(), null);
    }

    public static BasicCard of(String description) {
        return new BasicCard(null, description, null, null);
    }
}