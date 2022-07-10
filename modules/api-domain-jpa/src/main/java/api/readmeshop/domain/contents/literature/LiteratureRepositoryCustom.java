package api.readmeshop.domain.contents.literature;

import java.util.List;

public interface LiteratureRepositoryCustom {

    List<Literature> getPoetryList(int page, Long offset);
}
