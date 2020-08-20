package dev.riyenas.chatbot.domain.menu;

import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;

import java.time.LocalDate;
import java.util.List;

public interface MenuCustomRepository {
    List<Menu> findByCafeteriaType(Cafeteria type);
    List<Menu> findByCafeteriaTypeAndTodayGOE(Cafeteria type, LocalDate localDate);
}
