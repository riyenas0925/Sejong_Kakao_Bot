package dev.riyenas.chatbot.domain.cafeteria;

import dev.riyenas.chatbot.web.dto.cafeteria.CarouselMenuResponseDto;
import dev.riyenas.chatbot.web.dto.cafeteria.SimpleTextMenuResponseDto;
import dev.riyenas.chatbot.web.payload.SkillResponseTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CafeteriaSkillResponseEnum {
    SIMPLE_TEXT(Arrays.asList(
            CafeteriaTypeEnum.STUDENT_HALL
    )){
        @Override
        public SkillResponseTemplate reponse(List<Menu> menus){
            List<SimpleTextMenuResponseDto> responseDtos = menus.stream()
                    .map(SimpleTextMenuResponseDto::new)
                    .collect(Collectors.toList());

            StringBuilder msg = new StringBuilder();

            for(SimpleTextMenuResponseDto dto : responseDtos) {
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
        public SkillResponseTemplate reponse(List<Menu> menus){

            List<CarouselMenuResponseDto> responseDtos = menus.stream()
                    .map(CarouselMenuResponseDto::new)
                    .collect(Collectors.toList());

            StringBuilder msg = new StringBuilder();

            for(CarouselMenuResponseDto dto : responseDtos) {
                msg.append(dto.getDate());
                msg.append(" : ");
                msg.append(dto.getName());
                msg.append("\n");
            }

            return new SkillResponseTemplate()
                    .addSimpleText(msg.toString());

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

    abstract public SkillResponseTemplate reponse(List<Menu> menus);
}
