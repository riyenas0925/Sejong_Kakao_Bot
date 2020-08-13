package dev.riyenas.chatbot.domain.cafeteria;

import java.util.List;

public interface MenuCustomRepository {
    public List<Menu> findByCafeteriaType(CafeteriaTypeEnum type);
}
