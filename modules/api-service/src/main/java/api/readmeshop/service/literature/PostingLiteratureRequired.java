package api.readmeshop.service.literature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostingLiteratureRequired {

    private String email;
    private String title;
    private String contents;
    private Shape shape;
}
