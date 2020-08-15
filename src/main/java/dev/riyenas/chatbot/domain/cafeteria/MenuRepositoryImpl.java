package dev.riyenas.chatbot.domain.cafeteria;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

public class MenuRepositoryImpl extends QuerydslRepositorySupport implements MenuCustomRepository {

    public MenuRepositoryImpl() {
        super(Menu.class);
    }

    @Override
    public List<Menu> findByCafeteriaType(CafeteriaType type) {
        final QMenu menu = QMenu.menu;

        return from(menu)
                .where(menu.cafeteriaType.eq(type))
                .fetch();
    }

    @Override
    public List<Menu> findByCafeteriaTypeAndTodayGOE(CafeteriaType type, LocalDate localDate) {
        final QMenu menu = QMenu.menu;

        return from(menu)
                .where(menu.cafeteriaType.eq(type))
                .where(menu.localDate.goe(localDate))
                .fetch();
    }
}
