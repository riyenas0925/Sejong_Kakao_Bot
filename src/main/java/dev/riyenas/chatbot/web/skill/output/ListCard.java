package dev.riyenas.chatbot.web.skill.output;

import dev.riyenas.chatbot.web.skill.common.Button;
import dev.riyenas.chatbot.web.skill.common.ListItem;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ListCard {
    private ListItem header;
    private List<ListItem> items;
    private List<Button> buttons;

    public ListCard(ListItem header, List<ListItem> items, List<Button> buttons) {
        this.header = header;
        this.items = items;
        this.buttons = buttons;
    }

    public ListCard(ListCard listCard) {
        this.header = listCard.getHeader();
        this.items = listCard.getItems();
        this.buttons = listCard.getButtons();
    }

    public static ListCard of(ListItem header, List<ListItem> items, List<Button> buttons) {
        return new ListCard(header, items, buttons);
    }
}