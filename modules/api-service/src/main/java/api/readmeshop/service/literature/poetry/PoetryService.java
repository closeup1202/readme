package api.readmeshop.service.literature.poetry;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.contents.literature.poetry.Poetry;
import api.readmeshop.domain.contents.literature.poetry.PoetryShape;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.service.literature.PostLiteratureRequired;
import api.readmeshop.service.literature.Write;
import api.readmeshop.service.seek.Validations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PoetryService {

    private final Write write;
    private final Validations validations;

    public void write(PostLiteratureRequired required){
        Member writer = validations.isWriter(required.getEmail());
        Literature literature = getPoetry(required, writer);
        write.savePoetry(literature);
    }

    private Literature getPoetry(PostLiteratureRequired required, Member writer) {
        return Poetry.builder()
                        .title(required.getTitle())
                        .contents(required.getContents())
                        .shape((PoetryShape)required.getType())
                        .member(writer)
                        .build();
    }
}
