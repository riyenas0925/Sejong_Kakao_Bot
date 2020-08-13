package dev.riyenas.chatbot.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.CafeteriaTypeEnum;
import dev.riyenas.chatbot.domain.cafeteria.Menu;
import dev.riyenas.chatbot.domain.cafeteria.MenuRepository;
import dev.riyenas.chatbot.web.dto.cafeteria.GunjagwanMenuRequestDto;
import dev.riyenas.chatbot.web.dto.cafeteria.StudentHallMenuRequestDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CafeteriaTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("군자관, 가든뷰 메뉴날짜를 LocalDate로 변환")
    public void menuDateInStringToLocalDate() {
        //given
        String dateInString = "화(08/11)";

        //when
        GunjagwanMenuRequestDto dto = GunjagwanMenuRequestDto.builder()
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
        StudentHallMenuRequestDto dto = StudentHallMenuRequestDto.builder()
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
                        .type(CafeteriaTypeEnum.GARDEN_VIEW)
                        .build(),
                Menu.builder()
                        .type(CafeteriaTypeEnum.GUNJAGWAN)
                        .build(),
                Menu.builder()
                        .type(CafeteriaTypeEnum.GUNJAGWAN)
                        .build()
        );

        //when
        menuRepository.saveAll(menus);
        List<Menu> GunjagwanMenus = menuRepository.findByCafeteriaType(CafeteriaTypeEnum.GUNJAGWAN);


        //that
        Assertions.assertEquals(GunjagwanMenus.size(), 2);
    }
}
