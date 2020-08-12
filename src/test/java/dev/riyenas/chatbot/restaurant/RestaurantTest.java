package dev.riyenas.chatbot.restaurant;

import dev.riyenas.chatbot.domain.restaurant.Menu;
import dev.riyenas.chatbot.web.dto.restaurant.GunjagwanMenuRequestDto;
import dev.riyenas.chatbot.web.dto.restaurant.StudentHallRequestDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class RestaurantTest {

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
        StudentHallRequestDto dto = StudentHallRequestDto.builder()
                .build();

        Menu menu = dto.toEntity();

        //that
        Assertions.assertEquals(menu.getLocalDate().getYear(), Calendar.getInstance().get(Calendar.YEAR));
    }
}
