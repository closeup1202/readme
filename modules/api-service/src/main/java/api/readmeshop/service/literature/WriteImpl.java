package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.contents.literature.LiteratureRepository;
import api.readmeshop.domain.contents.literature.poetry.Poetry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WriteImpl implements Write{

    private final LiteratureRepository literatureRepository;

    @Override
    public void savePoetry(Literature literature) {
        literatureRepository.save(literature);
    }

}
