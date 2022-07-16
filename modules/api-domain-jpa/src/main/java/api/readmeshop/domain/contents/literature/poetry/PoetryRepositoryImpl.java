package api.readmeshop.domain.contents.literature.poetry;

import api.readmeshop.domain.contents.literature.Literature;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static api.readmeshop.domain.contents.literature.QLiterature.literature;

@RequiredArgsConstructor
public class PoetryRepositoryImpl implements PoetryRepositoryCustom {

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
