package dev.riyenas.chatbot.web.dto.restaurant;

import dev.riyenas.chatbot.domain.restaurant.Menu;
import dev.riyenas.chatbot.domain.restaurant.RestaurantTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@ToString
public class StudentHallRequestDto {
    private String name;
    private String price;
    private Date date;
    private RestaurantTypeEnum type;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .localDate(dateToLocalDate(date))
                .type(type)
                .build();
    }

    @Builder
    public StudentHallRequestDto(String name, String price, RestaurantTypeEnum type) {
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
