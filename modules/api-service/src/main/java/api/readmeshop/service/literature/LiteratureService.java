package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.service.seek.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiteratureService {

    private final Write write;
    private final Validation validation;
    private final ContentsList<Literature> contentsList;

    public void write(PostingLiteratureRequired required) {
        validation.isWriter(required.getEmail());
        write.savePoetry(required);
    }

}
