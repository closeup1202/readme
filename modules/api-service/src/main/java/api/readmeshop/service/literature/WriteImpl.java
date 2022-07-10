package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.contents.literature.LiteratureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WriteImpl implements Write{

    private final LiteratureRepository literatureRepository;

    @Override
    public void savePoetry(PostingLiteratureRequired required) {
        Literature literature = Literature.builder()
                              .title(required.getTitle())
                              .contents(required.getContents()).build();

        literatureRepository.save(literature);
    }
}
