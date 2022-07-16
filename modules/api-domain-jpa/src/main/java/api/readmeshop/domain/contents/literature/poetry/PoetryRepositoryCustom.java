package api.readmeshop.domain.contents.literature.poetry;

import api.readmeshop.domain.contents.literature.Literature;

import java.util.List;

public interface PoetryRepositoryCustom {

    List<Literature> getPoetryList(int page, Long offset);
}
