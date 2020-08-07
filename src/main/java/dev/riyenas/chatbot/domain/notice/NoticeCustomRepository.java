package dev.riyenas.chatbot.domain.notice;

import java.util.List;

public interface NoticeCustomRepository {
    public List<Notice> findByTypeDesc(NoticeTypeEnum type);
    public List<Notice> findAllDesc();
}
