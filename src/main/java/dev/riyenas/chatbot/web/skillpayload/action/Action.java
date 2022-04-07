package dev.riyenas.chatbot.web.skillpayload.action;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class Action {
    private String id;
    private String name;
    private Map<String, String> params;
//    private Map<String, DetailParams> detailParams; // TODO: 추후 구현 예정
//    private Map<String, Object> clientExtra; // TODO: 추후 구현 예정
}
