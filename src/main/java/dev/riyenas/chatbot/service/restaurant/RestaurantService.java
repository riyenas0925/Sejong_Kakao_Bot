package dev.riyenas.chatbot.service.restaurant;

import dev.riyenas.chatbot.domain.restaurant.Menu;
import dev.riyenas.chatbot.domain.restaurant.MenuRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final MenuRepository menuRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
