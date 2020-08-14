package dev.riyenas.chatbot.web.dto.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.CafeteriaType;
import dev.riyenas.chatbot.domain.cafeteria.MealTimeType;
import dev.riyenas.chatbot.domain.cafeteria.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@ToString
public class StudentHallMenuRequestDto {
    private String name;
    private String price;
    private Date date;
    private CafeteriaType type;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .localDate(dateToLocalDate(date))
                .cafeteriaType(type)
                .mealTimeType(MealTimeType.ANYTIME)
                .build();
    }

    @Builder
    public StudentHallMenuRequestDto(String name, String price, CafeteriaType type) {
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
