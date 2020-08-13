package dev.riyenas.chatbot.web.dto.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.Menu;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SimpleTextMenuResponseDto {
    private String name;
    private String price;

    public SimpleTextMenuResponseDto(Menu menu) {
        this.name = menu.getName();
        this.price = menu.getPrice();
    }
}
