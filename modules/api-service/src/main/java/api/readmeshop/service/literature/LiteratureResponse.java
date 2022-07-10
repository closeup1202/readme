package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.Literature;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LiteratureResponse {

    private final String email;
    private final String title;
    private final String contents;

    @Builder
    public LiteratureResponse(Literature literature){
        this.email = literature.getMember().getEmail();
        this.title = literature.getTitle();
        this.contents = literature.getContents();
    }
}
