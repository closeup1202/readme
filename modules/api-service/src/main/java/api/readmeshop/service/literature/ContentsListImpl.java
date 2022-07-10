package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.service.policies.posting.PostedPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentsListImpl implements ContentsList<Literature>{

    private final LiteratureRepository literatureRepository;

    @Override
    public List<Literature> getList(PostedPolicy postedPolicy) {
        return literatureRepository.getPoetryList(postedPolicy.getSize(), postedPolicy.getOffset());
    }
}
