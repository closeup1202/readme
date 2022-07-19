package api.readmeshop.domain.contents.literature.poetry;

import api.readmeshop.domain.contents.literature.Literature;

import java.util.List;

public interface PoetryRepositoryCustom {

    List<Poetry> getPoetryList(int size, Long offset);
}
