package dev.riyenas.chatbot.service.restaurant;

import dev.riyenas.chatbot.domain.restaurant.Menu;
import dev.riyenas.chatbot.domain.restaurant.MenuRepository;
import dev.riyenas.chatbot.domain.restaurant.RestaurantGroupEnum;
import dev.riyenas.chatbot.domain.restaurant.RestaurantTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class RestaurantCrawlerService {

    private final MenuRepository menuRepository;

    public void restaurantCrawler() throws IOException {
        for(RestaurantGroupEnum group : RestaurantGroupEnum.values()) {
            for(RestaurantTypeEnum restaurant : group.getRestaurantGroup()) {
                String url = group.getBaseUrl() + restaurant.getId();

                List<Menu> menus = restaurant.crawlMenu(url);
                menuRepository.saveAll(menus);

                log.info(menuRepository.findAll().toString());
            }
        }
    }
}