package dev.riyenas.chatbot.domain.cafeteria;

import dev.riyenas.chatbot.service.cafeteria.CafeteriaService;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;
import dev.riyenas.chatbot.web.skill.output.BasicCard;
import dev.riyenas.chatbot.web.skill.output.Carousel;
import dev.riyenas.chatbot.web.skill.output.CarouselEnum;

import java.time.LocalDate;
import java.util.*;

public enum CafeteriaSkillResponseEnum {
    SIMPLE_TEXT(Arrays.asList(
            CafeteriaTypeEnum.STUDENT_HALL
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
            CafeteriaTypeEnum.GUNJAGWAN,
            CafeteriaTypeEnum.GARDEN_VIEW
    )){
        @Override
        public SkillResponseTemplate response(List<Menu> menus){

            TreeMap<MealTimeEnum, TreeMap<LocalDate, List<Menu>>> menuGroup =
                    CafeteriaService.groupByMealTimeAndDate(menus);

            Map<MealTimeEnum, List<BasicCard>> basicCardGroup = new HashMap<>();

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
                            basicCardGroup.get(MealTimeEnum.LUNCH)

                    ))
                    .addCarousel(Carousel.of(
                            CarouselEnum.BASIC_CARD.getValue(),
                            basicCardGroup.get(MealTimeEnum.DINNER)
                    ));
        }
    };

    private List<CafeteriaTypeEnum> cafeteriaTypes;

    CafeteriaSkillResponseEnum(List<CafeteriaTypeEnum> cafeteriaTypes) {
        this.cafeteriaTypes = cafeteriaTypes;
    }

    public static CafeteriaSkillResponseEnum findByCafeteriaType(CafeteriaTypeEnum type){
        return Arrays.stream(CafeteriaSkillResponseEnum.values())
                .filter(cafeteriaResponseType -> cafeteriaResponseType.cafeteriaTypes.contains(type))
                .findAny()
                .orElse(CafeteriaSkillResponseEnum.SIMPLE_TEXT);
    }

    abstract public SkillResponseTemplate response(List<Menu> menus);
}
