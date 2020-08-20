package dev.riyenas.chatbot.domain.menu.cafeteria;

import dev.riyenas.chatbot.domain.menu.Menu;
import dev.riyenas.chatbot.service.cafeteria.CafeteriaService;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import dev.riyenas.chatbot.web.skill.common.ButtonEnum;
import dev.riyenas.chatbot.web.skill.output.BasicCard;
import dev.riyenas.chatbot.web.skill.output.Carousel;
import dev.riyenas.chatbot.web.skill.output.CarouselEnum;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Log4j2
public enum CafeteriaSkillResponse {
    SIMPLE_TEXT(Arrays.asList(
            Cafeteria.STUDENT_HALL
    )){
        @Override
        public SkillResponseTemplate response(List<Menu> menus) {

            StringBuilder msg = new StringBuilder();

            for(Menu dto : menus) {
                msg.append(dto.getName());
                msg.append(" (");
                msg.append(dto.getPrice());
                msg.append("원)\n");
            }

            return new SkillResponseTemplate()
                    .addSimpleText(msg.toString());
      }
    },
    CAROUSEL(Arrays.asList(
            Cafeteria.GUNJAGWAN,
            Cafeteria.GARDEN_VIEW
    )){
        @Override
        public SkillResponseTemplate response(List<Menu> menus) {

            TreeMap<MealTimeType, TreeMap<LocalDate, List<Menu>>> menuGroup =
                    CafeteriaService.groupByMealTimeAndDate(menus);

            Map<MealTimeType, List<BasicCard>> basicCardGroup = new HashMap<>();

            menuGroup.forEach((mealTimeType, value1) -> {
                List<BasicCard> basicCards = new ArrayList<>();
                value1.forEach((localDate, value2) -> {
                    String date = value2.get(0).getLocalDate().toString();
                    String dayOfWeek = value2.get(0).getLocalDate()
                            .getDayOfWeek()
                            .getDisplayName(TextStyle.SHORT, Locale.KOREAN);

                    String title = date + "(" + dayOfWeek + ") - " + mealTimeType.getValue();

                    basicCards.add(
                            BasicCard.of(
                                    title,
                                    value2.get(0).getName()
                            )
                    );
                });

                basicCardGroup.put(mealTimeType, basicCards);
            });

            SkillResponseTemplate skillResponseTemplate = new SkillResponseTemplate();

            for(MealTimeType type : MealTimeType.values()) {
                if(basicCardGroup.get(type) != null) {
                    skillResponseTemplate.addCarousel(Carousel.of(
                            CarouselEnum.BASIC_CARD.getValue(),
                            basicCardGroup.get(type)
                    ));
                };
            }

            if(basicCardGroup.isEmpty()) {
                return new SkillResponseTemplate()
                        .addBasicCard(BasicCard.of(
                                "현재 메뉴 정보가 없거나 휴무일입니다."
                                ,Arrays.asList(
                                        ButtonEnum.WEBLINK.action("자세히 보기", "")
                                )
                        ));
            }

            return skillResponseTemplate;
        }
    };

    private List<Cafeteria> cafeterias;

    CafeteriaSkillResponse(List<Cafeteria> cafeterias) {
        this.cafeterias = cafeterias;
    }

    public static CafeteriaSkillResponse findByCafeteriaType(Cafeteria type){
        return Arrays.stream(CafeteriaSkillResponse.values())
                .filter(cafeteriaResponseType -> cafeteriaResponseType.cafeterias.contains(type))
                .findAny()
                .orElse(CafeteriaSkillResponse.SIMPLE_TEXT);
    }

    abstract public SkillResponseTemplate response(List<Menu> menus);
}
