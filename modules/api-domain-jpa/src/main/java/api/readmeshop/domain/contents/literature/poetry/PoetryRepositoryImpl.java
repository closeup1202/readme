package api.readmeshop.domain.contents.literature.poetry;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static api.readmeshop.domain.contents.literature.poetry.QPoetry.*;

@RequiredArgsConstructor
public class PoetryRepositoryImpl implements PoetryRepositoryCustom {

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
