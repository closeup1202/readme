package api.readmeshop.request.literature;

import api.readmeshop.service.literature.Shape;
import api.readmeshop.service.literature.PostingLiteratureRequired;
import lombok.*;

@Getter
@NoArgsConstructor
public class PostingPoetry extends PostingLiteratureRequired {

    private String email;
    private String title;
    private String contents;
    private Shape shape;

    public PostingPoetry(String email, String title, String contents, Shape shape) {
        super(email, title, contents, shape);
    }

    @Builder
    public PostingPoetry(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
