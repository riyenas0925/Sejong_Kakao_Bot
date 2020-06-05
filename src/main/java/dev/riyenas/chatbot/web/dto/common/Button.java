package dev.riyenas.chatbot.web.dto.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Button {
    private String label;
    private String action;
    private String webLinkUrl;
    private String messageText;
    private String phoneNumber;
    private String blockId;

    public Button(String label, String action, String webLinkUrl, String messageText, String phoneNumber, String blockId) {
        this.label = label;
        this.action = action;
        this.webLinkUrl = webLinkUrl;
        this.messageText = messageText;
        this.phoneNumber = phoneNumber;
        this.blockId = blockId;
    }
}

