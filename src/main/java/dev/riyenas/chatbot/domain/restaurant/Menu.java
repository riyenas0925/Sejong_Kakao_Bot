package dev.riyenas.chatbot.domain.restaurant;

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
    private RestaurantTypeEnum type;

    @Builder
    public Menu(Long id, String name, String price, RestaurantTypeEnum type, LocalDate localDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.localDate = localDate;
    }
}
