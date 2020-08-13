package dev.riyenas.chatbot.service.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.Menu;
import dev.riyenas.chatbot.domain.cafeteria.MenuRepository;
import dev.riyenas.chatbot.domain.cafeteria.CafeteriaGroupEnum;
import dev.riyenas.chatbot.domain.cafeteria.CafeteriaTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CafeteriaCrawlerService {

    private final MenuRepository menuRepository;

    public void cafeteriaCrawler() throws IOException {
        for(CafeteriaGroupEnum group : CafeteriaGroupEnum.values()) {
            for(CafeteriaTypeEnum cafeteria : group.getCafeteriaGroup()) {
                String url = group.getBaseUrl() + cafeteria.getId();

                List<Menu> menus = cafeteria.crawlMenu(url);
                menuRepository.saveAll(menus);

                log.info(menuRepository.findAll().toString());
            }
        }
    }
}