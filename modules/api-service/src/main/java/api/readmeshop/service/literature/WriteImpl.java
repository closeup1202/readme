package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.domain.contents.literature.poetry.Poetry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static api.readmeshop.domain.contents.literature.poetry.PoetryShape.PROSE;

@RequiredArgsConstructor
@Component
public class WriteImpl implements Write{

    private final LiteratureRepository literatureRepository;

    @Override
    public void savePoetry(PostingLiteratureRequired required) {
        Literature literature = Poetry.builder()
                                    .title(required.getTitle())
                                    .contents(required.getContents())
                                    .shape(PROSE)
                                    .build();

        literatureRepository.save(literature);
    }
}
