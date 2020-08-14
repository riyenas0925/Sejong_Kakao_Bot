package dev.riyenas.chatbot.domain.cafeteria;

import dev.riyenas.chatbot.service.cafeteria.CafeteriaService;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import dev.riyenas.chatbot.web.skill.output.BasicCard;
import dev.riyenas.chatbot.web.skill.output.Carousel;
import dev.riyenas.chatbot.web.skill.output.CarouselEnum;

import java.time.LocalDate;
import java.util.*;

public enum CafeteriaSkillResponse {
    SIMPLE_TEXT(Arrays.asList(
            CafeteriaType.STUDENT_HALL
    )){
        @Override
        public SkillResponseTemplate response(List<Menu> menus){

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
            CafeteriaType.GUNJAGWAN,
            CafeteriaType.GARDEN_VIEW
    )){
        @Override
        public SkillResponseTemplate response(List<Menu> menus){

            TreeMap<MealTimeType, TreeMap<LocalDate, List<Menu>>> menuGroup =
                    CafeteriaService.groupByMealTimeAndDate(menus);

            Map<MealTimeType, List<BasicCard>> basicCardGroup = new HashMap<>();

            menuGroup.forEach((mealTimeType, value1) -> {
                List<BasicCard> basicCards = new ArrayList<>();

                value1.forEach((localDate, value2) -> {
                    basicCards.add(
                            BasicCard.of(
                                    value2.get(0).getLocalDate() + " (" + mealTimeType.getValue() + ")",
                                    value2.get(0).getName()
                            )
                    );
                });

                basicCardGroup.put(mealTimeType, basicCards);
            });

            return new SkillResponseTemplate()
                    .addCarousel(Carousel.of(
                            CarouselEnum.BASIC_CARD.getValue(),
                            basicCardGroup.get(MealTimeType.LUNCH)

                    ))
                    .addCarousel(Carousel.of(
                            CarouselEnum.BASIC_CARD.getValue(),
                            basicCardGroup.get(MealTimeType.DINNER)
                    ));
        }
    };

    private List<CafeteriaType> cafeteriaTypes;

    CafeteriaSkillResponse(List<CafeteriaType> cafeteriaTypes) {
        this.cafeteriaTypes = cafeteriaTypes;
    }

    public static CafeteriaSkillResponse findByCafeteriaType(CafeteriaType type){
        return Arrays.stream(CafeteriaSkillResponse.values())
                .filter(cafeteriaResponseType -> cafeteriaResponseType.cafeteriaTypes.contains(type))
                .findAny()
                .orElse(CafeteriaSkillResponse.SIMPLE_TEXT);
    }

    abstract public SkillResponseTemplate response(List<Menu> menus);
}
