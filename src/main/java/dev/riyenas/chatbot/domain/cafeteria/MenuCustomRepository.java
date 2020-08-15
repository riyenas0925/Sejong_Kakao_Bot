package dev.riyenas.chatbot.domain.cafeteria;

import java.time.LocalDate;
import java.util.List;

public interface MenuCustomRepository {
    List<Menu> findByCafeteriaType(CafeteriaType type);
    List<Menu> findByCafeteriaTypeAndTodayGOE(CafeteriaType type, LocalDate localDate);
}
