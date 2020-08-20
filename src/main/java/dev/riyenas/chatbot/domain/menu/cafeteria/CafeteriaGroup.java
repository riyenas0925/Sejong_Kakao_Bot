package dev.riyenas.chatbot.domain.menu.cafeteria;

import java.util.Arrays;
import java.util.List;

public enum CafeteriaGroup {
    CAFETERIA("http://m.sejong.ac.kr/front/cafeteria.do?type1=",
            Arrays.asList(
                    Cafeteria.GARDEN_VIEW,
                    Cafeteria.GUNJAGWAN,
                    Cafeteria.WOOJUNGDANG
            )
    ),CAFETERIA_2("http://m.sejong.ac.kr/front/cafeteria2.do?type1=",
            Arrays.asList(
                    Cafeteria.STUDENT_HALL
            )
    );

    private String baseUrl;
    private List<Cafeteria> cafeteriaGroup;

    CafeteriaGroup(String baseUrl, List<Cafeteria> cafeteriaGroup) {
        this.baseUrl = baseUrl;
        this.cafeteriaGroup = cafeteriaGroup;
    }

    public List<Cafeteria> getCafeteriaGroup() {
        return cafeteriaGroup;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
