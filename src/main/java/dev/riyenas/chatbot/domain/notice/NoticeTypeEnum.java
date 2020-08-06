package dev.riyenas.chatbot.domain.notice;

import java.util.Arrays;

public enum NoticeTypeEnum {
    NORMAL("일반", "333"),
    ENTRANCE("입학", "334"),
    SCHOOL("학사", "335"),
    JOB("취업", "337"),
    SCHOLARSHIP("장학", "338"),
    RECRUITMENT("교내모집", "339");

    private String title;
    private String fk;

    NoticeTypeEnum(String title, String fk){
        this.title = title;
        this.fk = fk;
    }

    public static NoticeTypeEnum findByTitle(String title){
        return Arrays.stream(NoticeTypeEnum.values())
                .filter(noticeType -> noticeType.getTitle().equals(title))
                .findAny()
                .orElse(NoticeTypeEnum.NORMAL);
    }

    public String getFk() {
        return fk;
    }

    public String getTitle(){
        return title;
    }
}
