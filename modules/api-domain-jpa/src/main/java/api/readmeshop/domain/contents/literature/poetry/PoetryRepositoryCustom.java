package api.readmeshop.domain.contents.literature.poetry;

import java.util.List;

public interface PoetryRepositoryCustom {

    List<Poetry> getPoetryList(int size, Long offset);
}
