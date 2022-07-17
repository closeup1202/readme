package api.readmeshop.request.literature.poetry;

import api.readmeshop.service.literature.PostLiteratureRequired;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostLiteratureRequest {

    private String type;
    private String email;
    private String contents;
    private String title;

    public PostLiteratureRequired getPostLiteratureRequired() {
        return PostLiteratureRequired.builder()
                .email(this.getEmail())
                .contents(this.getContents())
                .title(this.getTitle())
                .typeStr(this.getType())
                .build();
    }
}
