package api.readmeshop.request.literature;

import api.readmeshop.service.policies.posting.PostedPolicy;
import lombok.*;

@Getter
public class PostedLiterature extends PostedPolicy {

    private Integer page;
    private Integer size;

    @Builder
    public PostedLiterature(PostedLiterature posting) {
        super(posting.page, posting.size);
    }
}
