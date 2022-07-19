package api.readmeshop.domain.contents.literature;

import api.readmeshop.domain.contents.literature.poetry.Poetry;

import java.util.List;

public interface LiteratureRepositoryCustom {

    List<Poetry> getPoetryList(int size, Long offset);
}
