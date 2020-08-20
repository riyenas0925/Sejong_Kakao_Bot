package dev.riyenas.chatbot.cafeteria;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.domain.menu.MenuRepository;
import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import dev.riyenas.chatbot.domain.menu.cafeteria.MealTimeType;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaService;
import dev.riyenas.chatbot.web.dto.cafeteria.MenuAndPriceRequestDto;
import dev.riyenas.chatbot.web.dto.cafeteria.MenuByDateAndMealTimeRequestDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CafeteriaTest {
    private static Logger log = LoggerFactory.getLogger(CafeteriaTest.class);

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("군자관, 가든뷰 메뉴날짜를 LocalDate로 변환")
    public void menuDateInStringToLocalDate() {
        //given
        String dateInString = "화(08/11)";

        //when
        MenuByDateAndMealTimeRequestDto dto = MenuByDateAndMealTimeRequestDto.builder()
                .date(dateInString)
                .build();

        Menu menu = dto.toEntity();

        //that
        Assertions.assertEquals(menu.getLocalDate().getMonth(), Month.AUGUST);
        Assertions.assertEquals(menu.getLocalDate().getDayOfMonth(), 11);
    }

    @Test
    @DisplayName("학생회관, 메뉴날짜를 오늘로 저장하고 LocalDate로 변환")
    public void dateToLocalDate() {
        //given
        Date date = new Date();

        //when
        MenuAndPriceRequestDto dto = MenuAndPriceRequestDto.builder()
                .build();

        Menu menu = dto.toEntity();

        //that
        Assertions.assertEquals(menu.getLocalDate().getYear(), Calendar.getInstance().get(Calendar.YEAR));
    }

    @Test
    @DisplayName("메뉴에서 Cafeteria종류로 검색")
    public void findByCafeteriaType() {
        //given
        List<Menu> menus = Arrays.asList(
                Menu.builder()
                        .cafeteria(Cafeteria.GARDEN_VIEW)
                        .build(),
                Menu.builder()
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build(),
                Menu.builder()
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build()
        );

        //when
        menuRepository.saveAll(menus);
        List<Menu> GunjagwanMenus = menuRepository.findByCafeteriaType(Cafeteria.GUNJAGWAN);


        //that
        Assertions.assertEquals(GunjagwanMenus.size(), 2);
    }

    @Test
    @DisplayName("Menu List를 날짜로 묶기")
    public void menuListGroupByLocalDate() {
        //given
        List<Menu> menus = Arrays.asList(
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 20))
                        .mealTimeType(MealTimeType.LUNCH)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 20))
                        .mealTimeType(MealTimeType.DINNER)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 21))
                        .mealTimeType(MealTimeType.LUNCH)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 21))
                        .mealTimeType(MealTimeType.DINNER)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 22))
                        .mealTimeType(MealTimeType.LUNCH)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 22))
                        .mealTimeType(MealTimeType.DINNER)
                        .build()
        );

        //when
        TreeMap<MealTimeType, TreeMap<LocalDate, List<Menu>>> menuGroup =
                CafeteriaService.groupByMealTimeAndDate(menus);

        Assertions.assertEquals(menuGroup.get(MealTimeType.LUNCH).size(), 3);
        Assertions.assertEquals(menuGroup.get(MealTimeType.DINNER).size(), 3);
    }

    @Test
    public void findByCafeteriaTypeAndAfterToday() {
        //given
        List<Menu> menus = Arrays.asList(
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 14))
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 15))
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 16))
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build()
        );

        //when
        menuRepository.saveAll(menus);
        List<Menu> menuList = menuRepository.findByCafeteriaTypeAndTodayGOE(
                Cafeteria.GUNJAGWAN, LocalDate.of(2020,8,15)
        );

        //that
        Assertions.assertEquals(menuList.size(), 2);
    }

    @Test
    public void findByCafeteriaTypeAndAfterTodayIsNull() {
        //given
        List<Menu> menus = Arrays.asList(
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 14))
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build(),
                Menu.builder()
                        .localDate(LocalDate.of(2020, 8, 15))
                        .cafeteria(Cafeteria.GUNJAGWAN)
                        .build()
        );

        //when
        menuRepository.saveAll(menus);
        List<Menu> menuList = menuRepository.findByCafeteriaTypeAndTodayGOE(
                Cafeteria.GUNJAGWAN, LocalDate.of(2020,8,16)
        );

        //that
        log.info(menuList.toString());
    }
}
