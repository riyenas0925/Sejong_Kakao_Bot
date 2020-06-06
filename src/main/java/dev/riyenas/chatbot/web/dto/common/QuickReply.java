package dev.riyenas.chatbot.web.dto.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class QuickReply {
    private String label;
    private String action;
    private String messageText;
    private String blockId;
    private Object extra;

    public QuickReply(String label, String action, String messageText, String blockId, Object extra){
        this.label = label;
        this.action = action;
        this.messageText = messageText;
        this.blockId = blockId;
        this.extra = extra;
    }
}
