package api.readmeshop.domain.contents.literature;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static api.readmeshop.domain.contents.literature.QLiterature.*;

@RequiredArgsConstructor
public class LiteratureRepositoryImpl implements LiteratureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Literature> getPoetryList(int size, Long offset) {
        return jpaQueryFactory.selectFrom(literature)
                        .limit(size)
                        .offset(offset)
                        .orderBy(literature.id.desc())
                        .fetch();
    }
}
