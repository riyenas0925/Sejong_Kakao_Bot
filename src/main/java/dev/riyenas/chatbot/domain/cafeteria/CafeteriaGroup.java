package dev.riyenas.chatbot.domain.cafeteria;

import java.util.Arrays;
import java.util.List;

public enum CafeteriaGroup {
    CAFETERIA("http://m.sejong.ac.kr/front/cafeteria.do?type1=",
            Arrays.asList(
                    CafeteriaType.GARDEN_VIEW,
                    CafeteriaType.GUNJAGWAN,
                    CafeteriaType.WOOJUNGDANG
            )
    ),CAFETERIA_2("http://m.sejong.ac.kr/front/cafeteria2.do?type1=",
            Arrays.asList(
                    CafeteriaType.STUDENT_HALL
            )
    );

    private String baseUrl;
    private List<CafeteriaType> cafeteriaGroup;

    CafeteriaGroup(String baseUrl, List<CafeteriaType> cafeteriaGroup) {
        this.baseUrl = baseUrl;
        this.cafeteriaGroup = cafeteriaGroup;
    }

    public List<CafeteriaType> getCafeteriaGroup() {
        return cafeteriaGroup;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
