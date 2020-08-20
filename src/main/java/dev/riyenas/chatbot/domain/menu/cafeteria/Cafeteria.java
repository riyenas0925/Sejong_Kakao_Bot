package dev.riyenas.chatbot.domain.menu.cafeteria;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaCrawlerService;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log4j2
public enum Cafeteria {
    GARDEN_VIEW("가든뷰", 1L){
        @Override
        public List<Menu> crawlMenu(String url) throws IOException {
            return CafeteriaCrawlerService.crawlMenuByDateType(url, this);
        }
    },
    GUNJAGWAN("군자관", 3L){
        @Override
        public List<Menu> crawlMenu(String url) throws IOException {
            return CafeteriaCrawlerService.crawlMenuByDateType(url, this);
        }
    },
    STUDENT_HALL("학생회관", 0L){
        @Override
        public List<Menu> crawlMenu(String url) throws IOException {
            return CafeteriaCrawlerService.crawlMenuAndPriceType(url, this);
        }
    },
    WOOJUNGDANG("우정당", 2L){
        @Override
        public List<Menu> crawlMenu(String url) {
            List<Menu> menus = Arrays.asList(
                    Menu.builder()
                            .name("우정당 메뉴")
                            .price("-")
                            .cafeteria(this)
                            .build()
            );
            return menus;
        }
    };

    private String title;
    private Long id;

    abstract public List<Menu> crawlMenu(String url) throws IOException;

    public static Cafeteria findBytitle(String title){
        return Arrays.stream(Cafeteria.values())
                .filter(cafeteriaType -> cafeteriaType.getTitle().equals(title))
                .findAny()
                .orElse(Cafeteria.STUDENT_HALL);
    }

    Cafeteria(String title, Long id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
