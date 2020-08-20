package dev.riyenas.chatbot.domain.menu;

import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import dev.riyenas.chatbot.domain.menu.cafeteria.MealTimeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@NoArgsConstructor
@Getter
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate localDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Cafeteria cafeteria;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MealTimeType mealTimeType;

    @Builder
    public Menu(Long id, String name, String price, LocalDate localDate, Cafeteria cafeteria, MealTimeType mealTimeType){
        this.id = id;
        this.name = name;
        this.price = price;
        this.localDate = localDate;
        this.cafeteria = cafeteria;
        this.mealTimeType = mealTimeType;
    }

}
