package dev.riyenas.chatbot.service.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.*;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
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

    public SkillResponseTemplate findByCafeteriaType(CafeteriaTypeEnum type) {
        List<Menu> menus = menuRepository.findByCafeteriaType(type);
        return CafeteriaSkillResponseEnum.findByCafeteriaType(type).response(menus);
    }

    public static TreeMap<MealTimeEnum, TreeMap<LocalDate, List<Menu>>> groupByMealTimeAndDate(List<Menu> menus) {
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
