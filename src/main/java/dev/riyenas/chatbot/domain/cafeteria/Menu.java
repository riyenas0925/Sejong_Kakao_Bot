package dev.riyenas.chatbot.domain.cafeteria;

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
    private CafeteriaTypeEnum cafeteriaType;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MealTimeEnum mealTimeType;

    @Builder
    public Menu(Long id, String name, String price, LocalDate localDate, CafeteriaTypeEnum cafeteriaType, MealTimeEnum mealTimeType){
        this.id = id;
        this.name = name;
        this.price = price;
        this.localDate = localDate;
        this.cafeteriaType = cafeteriaType;
        this.mealTimeType = mealTimeType;
    }

}
