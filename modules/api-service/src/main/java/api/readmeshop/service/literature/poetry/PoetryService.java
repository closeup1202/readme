package api.readmeshop.service.literature.poetry;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.service.literature.LiteratureFactory;
import api.readmeshop.service.literature.LiteratureResponse;
import api.readmeshop.service.literature.PostLiteratureRequired;
import api.readmeshop.service.literature.Write;
import api.readmeshop.service.policies.posting.PostedPolicy;
import api.readmeshop.service.seek.Validations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoetryService {

    private final Write write;
    private final Validations validations;
    private final LiteratureFactory factory;

    public void write(PostLiteratureRequired required){
        Member writer = validations.isWriter(required.getEmail());
        Literature literature = factory.setPoetry(required, writer);
        write.savePoetry(literature);
    }

    public List<LiteratureResponse> getList(PostedPolicy policy) {
        return factory.getPoetryList(policy).stream()
                        .map(LiteratureResponse::new)
                        .collect(Collectors.toList());
    }

}
