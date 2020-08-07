package dev.riyenas.chatbot.domain.notice;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeCustomRepository {

    public NoticeRepositoryImpl() {
        super(Notice.class);
    }


}
