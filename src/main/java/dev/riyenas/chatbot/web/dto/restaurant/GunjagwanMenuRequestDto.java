package dev.riyenas.chatbot.web.dto.restaurant;

import dev.riyenas.chatbot.domain.restaurant.Menu;
import dev.riyenas.chatbot.domain.restaurant.RestaurantTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Locale;

@Getter
@ToString
public class GunjagwanMenuRequestDto {
    private String name;
    private String price;
    private String date;
    private RestaurantTypeEnum type;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .localDate(stringDateToLocalDate(date))
                .type(type)
                .build();
    }

    public static LocalDate stringDateToLocalDate(String dateInString) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("E(M/d)")
                .parseDefaulting(ChronoField.YEAR, Calendar.getInstance().get(Calendar.YEAR))
                .toFormatter(Locale.KOREAN);

        return LocalDate.parse(dateInString, formatter);
    }

    @Builder
    public GunjagwanMenuRequestDto(String name, String price, String date, RestaurantTypeEnum type) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.type = type;
    }
}
