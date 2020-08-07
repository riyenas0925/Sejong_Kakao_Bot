package dev.riyenas.chatbot.domain.notice;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeCustomRepository {

    public NoticeRepositoryImpl() {
        super(Notice.class);
    }


    @Override
    public List<Notice> findByTypeDesc(NoticeTypeEnum type) {
        final QNotice notice = QNotice.notice;

        return from(notice)
                .where(notice.type.eq(type))
                .orderBy(notice.date.desc())
                .limit(5L)
                .fetch();
    }

    @Override
    public List<Notice> findAllDesc() {
        final QNotice notice = QNotice.notice;

        return from(notice)
                .orderBy(notice.date.desc())
                .limit(5L)
                .fetch();
    }
}
