package dev.riyenas.chatbot.web.dto.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.Menu;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CarouselMenuResponseDto {
    private String name;
    private String date;

    public CarouselMenuResponseDto(Menu menu) {
        this.name = menu.getName();
        this.date = menu.getLocalDate().toString();
    }
}
