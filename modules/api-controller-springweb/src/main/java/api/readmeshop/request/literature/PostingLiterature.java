package api.readmeshop.request.literature;

import api.readmeshop.service.literature.PostingLiteratureRequired;
import lombok.*;

@Getter
@NoArgsConstructor
public class PostingLiterature extends PostingLiteratureRequired {

    private String email;
    private String title;
    private String contents;

    public PostingLiterature(String email, String title, String contents) {
        super(email, title, contents);
        this.email = email;
        this.title = title;
        this.contents = contents;
    }

    @Builder
    public PostingLiterature(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
