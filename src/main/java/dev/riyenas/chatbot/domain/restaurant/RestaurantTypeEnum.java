package dev.riyenas.chatbot.domain.restaurant;

import dev.riyenas.chatbot.web.dto.restaurant.GardenViewMenuRequestDto;
import dev.riyenas.chatbot.web.dto.restaurant.GunjagwanMenuRequestDto;
import dev.riyenas.chatbot.web.dto.restaurant.StudentHallRequestDto;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum RestaurantTypeEnum {
    GARDEN_VIEW("가든뷰", 1L){
        @Override
        public List<Menu> crawlMenu(String url) throws IOException {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div > table > tbody > tr");

            List<Integer> dateSeq = Arrays.asList(0,2,4,6,8,10);
            List<GardenViewMenuRequestDto> menus = new ArrayList<>();

            String date = "";

            for(int i = 0; i < elements.size(); i++){
                Element element = elements.get(i);
                String menu = element.select(".td").text();

                if(dateSeq.contains(i)){
                    date = element.select(".th").text()
                            .replace(" 중식", "");
                }

                menus.add(
                        GardenViewMenuRequestDto.builder()
                                .name(menu)
                                .date(date)
                                .price("-")
                                .type(this)
                                .build()
                );
            }

            return menus.stream()
                    .map(GardenViewMenuRequestDto::toEntity)
                    .collect(Collectors.toList());
        }
    },
    WOOJUNGDANG("우정당", 2L){
        @Override
        public List<Menu> crawlMenu(String url) {
            List<Menu> menus = Arrays.asList(
                    Menu.builder()
                            .name("우정당 메뉴")
                            .price("-")
                            .type(this)
                            .build()
            );
            return menus;
        }
    },
    GUNJAGWAN("군자관", 3L){
        @Override
        public List<Menu> crawlMenu(String url) throws IOException {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div > table > tbody > tr");

            List<Integer> dateSeq = Arrays.asList(0,2,4,6,8,10);
            List<GunjagwanMenuRequestDto> menus = new ArrayList<>();

            String date = "";

            for(int i = 0; i < elements.size(); i++){
                Element element = elements.get(i);
                String menu = element.select(".td").text();

                if(dateSeq.contains(i)){
                    date = element.select(".th").text()
                    .replace(" 중식", "");
                }

                menus.add(
                        GunjagwanMenuRequestDto.builder()
                                .name(menu)
                                .date(date)
                                .price("-")
                                .type(this)
                                .build()
                );
            }

            return menus.stream()
                    .map(GunjagwanMenuRequestDto::toEntity)
                    .collect(Collectors.toList());
        }
    },
    STUDENT_HALL("학생회관", 0L){
        @Override
        public List<Menu> crawlMenu(String url) throws IOException {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div > table > tbody > tr");

            List<StudentHallRequestDto> menus = new ArrayList<>();

            for(Element element : elements) {
                String menu = element.select(".th").text();
                String price = element.select(".td").text()
                        .replace("원", "");

                menus.add(
                        StudentHallRequestDto.builder()
                                .name(menu)
                                .price(price)
                                .type(this)
                                .build()
                );
            }

            return menus.stream()
                    .map(StudentHallRequestDto::toEntity)
                    .collect(Collectors.toList());
        }
    };

    private String title;
    private Long id;

    abstract public List<Menu> crawlMenu(String url) throws IOException;

    RestaurantTypeEnum(String title, Long id) {
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
