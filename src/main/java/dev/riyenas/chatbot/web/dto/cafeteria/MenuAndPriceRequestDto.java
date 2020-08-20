package dev.riyenas.chatbot.web.dto.cafeteria;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import dev.riyenas.chatbot.domain.menu.cafeteria.MealTimeType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@ToString
public class MenuAndPriceRequestDto {
    private String name;
    private String price;
    private Date date;
    private Cafeteria type;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .localDate(dateToLocalDate(date))
                .cafeteria(type)
                .mealTimeType(MealTimeType.ANYTIME)
                .build();
    }

    @Builder
    public MenuAndPriceRequestDto(String name, String price, Cafeteria type) {
        this.name = name;
        this.price = price;
        this.date = new Date();
        this.type = type;
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
