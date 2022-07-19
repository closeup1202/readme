package api.readmeshop.domain.contents.literature;

import api.readmeshop.domain.contents.literature.poetry.Poetry;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static api.readmeshop.domain.contents.literature.poetry.QPoetry.poetry;

@RequiredArgsConstructor
public class LiteratureRepositoryImpl implements LiteratureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Poetry> getPoetryList(int size, Long offset) {
        return jpaQueryFactory.selectFrom(poetry)
                        .limit(size)
                        .offset(offset)
                        .orderBy(poetry.id.desc())
                        .fetch();
    }
}
