package dev.riyenas.chatbot.web.dto.cafeteria;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import dev.riyenas.chatbot.domain.menu.cafeteria.MealTimeType;
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
public class MenuByDateAndMealTimeRequestDto {
    private String name;
    private String price;
    private String date;
    private Cafeteria cafeteria;
    private MealTimeType mealTimeType;

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .price(price)
                .localDate(stringDateToLocalDate(date))
                .cafeteria(cafeteria)
                .mealTimeType(mealTimeType)
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
    public MenuByDateAndMealTimeRequestDto(String name, String price, String date, Cafeteria cafeteria,
                                           MealTimeType mealTimeType) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.cafeteria = cafeteria;
        this.mealTimeType = mealTimeType;
    }
}
