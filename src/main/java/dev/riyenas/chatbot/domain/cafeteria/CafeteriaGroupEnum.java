package dev.riyenas.chatbot.domain.cafeteria;

import java.util.Arrays;
import java.util.List;

public enum CafeteriaGroupEnum {
    CAFETERIA("http://m.sejong.ac.kr/front/cafeteria.do?type1=",
            Arrays.asList(
                    CafeteriaTypeEnum.GARDEN_VIEW,
                    CafeteriaTypeEnum.GUNJAGWAN,
                    CafeteriaTypeEnum.WOOJUNGDANG
            )
    ),CAFETERIA_2("http://m.sejong.ac.kr/front/cafeteria2.do?type1=",
            Arrays.asList(
                    CafeteriaTypeEnum.STUDENT_HALL
            )
    );

    private String baseUrl;
    private List<CafeteriaTypeEnum> cafeteriaGroup;

    CafeteriaGroupEnum(String baseUrl, List<CafeteriaTypeEnum> cafeteriaGroup) {
        this.baseUrl = baseUrl;
        this.cafeteriaGroup = cafeteriaGroup;
    }

    public List<CafeteriaTypeEnum> getCafeteriaGroup() {
        return cafeteriaGroup;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
