package api.readmeshop.request.literature.poetry;

import api.readmeshop.service.literature.PostLiteratureRequired;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostPoetryRequest extends PostLiteratureRequired {

    private String poetryType;

    public PostPoetryRequest(PostPoetryRequest request) {
        super(request.getEmail(), request.getTitle(), request.getContents(), request.getPoetryType());
    }
}
