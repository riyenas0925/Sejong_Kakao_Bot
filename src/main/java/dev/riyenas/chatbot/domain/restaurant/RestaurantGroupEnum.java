package dev.riyenas.chatbot.domain.restaurant;

import java.util.Arrays;
import java.util.List;

public enum RestaurantGroupEnum {
    CAFETERIA("http://m.sejong.ac.kr/front/cafeteria.do?type1=",
            Arrays.asList(
                    RestaurantTypeEnum.GARDEN_VIEW,
                    RestaurantTypeEnum.GUNJAGWAN,
                    RestaurantTypeEnum.WOOJUNGDANG
            )
    ),CAFETERIA_2("http://m.sejong.ac.kr/front/cafeteria2.do?type1=",
            Arrays.asList(
                    RestaurantTypeEnum.STUDENT_HALL
            )
    );

    private String baseUrl;
    private List<RestaurantTypeEnum> restaurantGroup;

    RestaurantGroupEnum(String baseUrl, List<RestaurantTypeEnum> restaurantGroup) {
        this.baseUrl = baseUrl;
        this.restaurantGroup = restaurantGroup;
    }

    public List<RestaurantTypeEnum> getRestaurantGroup() {
        return restaurantGroup;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
