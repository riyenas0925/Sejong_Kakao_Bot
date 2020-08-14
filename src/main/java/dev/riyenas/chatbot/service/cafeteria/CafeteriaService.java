package dev.riyenas.chatbot.service.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.CafeteriaType;
import dev.riyenas.chatbot.domain.cafeteria.MealTimeType;
import dev.riyenas.chatbot.domain.cafeteria.Menu;
import dev.riyenas.chatbot.domain.cafeteria.MenuRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor
public class CafeteriaService {
    private final MenuRepository menuRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public List<Menu> findByCafeteriaType(CafeteriaType type) {
        return menuRepository.findByCafeteriaType(type);
    }

    public static TreeMap<MealTimeType, TreeMap<LocalDate, List<Menu>>> groupByMealTimeAndDate(List<Menu> menus) {
        return menus.stream()
                .collect(
                        Collectors.groupingBy(
                                Menu::getMealTimeType,
                                TreeMap::new,
                                Collectors.groupingBy(
                                        Menu::getLocalDate,
                                        TreeMap::new,
                                        Collectors.toList()
                                )
                        )
                );
    }
}
