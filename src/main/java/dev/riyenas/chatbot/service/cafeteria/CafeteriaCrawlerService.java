package dev.riyenas.chatbot.service.cafeteria;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.domain.menu.MenuRepository;
import dev.riyenas.chatbot.domain.menu.cafeteria.Cafeteria;
import dev.riyenas.chatbot.domain.menu.cafeteria.CafeteriaGroup;
import dev.riyenas.chatbot.domain.menu.cafeteria.MealTimeType;
import dev.riyenas.chatbot.web.dto.cafeteria.MenuAndPriceRequestDto;
import dev.riyenas.chatbot.web.dto.cafeteria.MenuByDateAndMealTimeRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CafeteriaCrawlerService {

    private final MenuRepository menuRepository;

    public void cafeteriaCrawler() throws IOException {
        menuRepository.deleteAll();

        for(CafeteriaGroup group : CafeteriaGroup.values()) {
            for(Cafeteria cafeteria : group.getCafeteriaGroup()) {
                String url = group.getBaseUrl() + cafeteria.getId();
                List<Menu> menus = cafeteria.crawlMenu(url);
                menuRepository.saveAll(menus);
            }
        }
    }

    public static List<Menu> crawlMenuByDateType(String url, Cafeteria type) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div > table > tbody > tr");

        List<Integer> dateSeq = Arrays.asList(0, 2, 4, 6, 8, 10);
        List<MenuByDateAndMealTimeRequestDto> menus = new ArrayList<>();

        String date = "";

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String menu = element.select(".td").text();

            if (dateSeq.contains(i)) {
                date = element.select(".th").text()
                        .replace(" 중식", "");
            }

            MealTimeType mealTimeType = ((i % 2) == 0) ? MealTimeType.LUNCH : MealTimeType.DINNER;
            menu = menu.isEmpty() ? "메뉴 없음" : menu;

            menus.add(
                    MenuByDateAndMealTimeRequestDto.builder()
                            .name(menu)
                            .date(date)
                            .price("-")
                            .cafeteria(type)
                            .mealTimeType(mealTimeType)
                            .build()
            );
        }

        return menus.stream()
                .map(MenuByDateAndMealTimeRequestDto::toEntity)
                .collect(Collectors.toList());
    }

    public static List<Menu> crawlMenuAndPriceType(String url, Cafeteria type) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div > table > tbody > tr");

        List<MenuAndPriceRequestDto> menus = new ArrayList<>();

        for(Element element : elements) {
            String menu = element.select(".th").text();
            String price = element.select(".td").text()
                    .replace("원", "");

            menus.add(
                    MenuAndPriceRequestDto.builder()
                            .name(menu)
                            .price(price)
                            .type(type)
                            .build()
            );
        }

        return menus.stream()
                .map(MenuAndPriceRequestDto::toEntity)
                .collect(Collectors.toList());
    }
}