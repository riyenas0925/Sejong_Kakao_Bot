package dev.riyenas.chatbot.web.dto.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.CafeteriaTypeEnum;
import dev.riyenas.chatbot.domain.cafeteria.MealTimeEnum;
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
    private CafeteriaTypeEnum type;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .localDate(dateToLocalDate(date))
                .cafeteriaType(type)
                .mealTimeType(MealTimeEnum.ANYTIME)
                .build();
    }

    @Builder
    public StudentHallMenuRequestDto(String name, String price, CafeteriaTypeEnum type) {
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
