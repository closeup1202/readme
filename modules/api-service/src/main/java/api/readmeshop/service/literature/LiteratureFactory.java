package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.domain.contents.literature.poetry.Poetry;
import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.service.policies.posting.PostedPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LiteratureFactory {

    private final LiteratureRepository literatureRepository;

    public Literature setPoetry(PostLiteratureRequired required, Member writer) {
        return Poetry.builder()
                .title(required.getTitle())
                .contents(required.getContents())
                .shape((PoetryShape)required.getType())
                .member(writer)
                .build();
    }

    public List<Poetry> getPoetryList(PostedPolicy policy){
        return literatureRepository.getPoetryList(policy.getSize(), policy.getOffset());
    }
}
