package dev.riyenas.chatbot.domain.menu;

import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

public class MenuRepositoryImpl extends QuerydslRepositorySupport implements MenuCustomRepository {

    public MenuRepositoryImpl() {
        super(Menu.class);
    }

    @Override
    public List<Menu> findByCafeteriaType(Cafeteria type) {
        final QMenu menu = QMenu.menu;

        return from(menu)
                .where(menu.cafeteria.eq(type))
                .fetch();
    }

    @Override
    public List<Menu> findByCafeteriaTypeAndTodayGOE(Cafeteria type, LocalDate localDate) {
        final QMenu menu = QMenu.menu;

        return from(menu)
                .where(menu.cafeteria.eq(type))
                .where(menu.localDate.goe(localDate))
                .fetch();
    }
}
