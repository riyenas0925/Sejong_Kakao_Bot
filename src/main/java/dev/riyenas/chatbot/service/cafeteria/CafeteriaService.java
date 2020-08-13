package dev.riyenas.chatbot.service.cafeteria;

import dev.riyenas.chatbot.domain.cafeteria.Menu;
import dev.riyenas.chatbot.domain.cafeteria.MenuRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class CafeteriaService {
    private final MenuRepository menuRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
