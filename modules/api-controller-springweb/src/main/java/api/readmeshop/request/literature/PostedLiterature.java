package api.readmeshop.request.literature;

import api.readmeshop.service.policies.posting.PostedPolicy;
import lombok.*;

@Getter
@AllArgsConstructor
public class PostedLiterature {

    private Integer page;
    private Integer size;

    @Builder
    public PostedPolicy postedPolicy() {
        return PostedPolicy.builder()
                .page(this.page)
                .size(this.size)
                .build();
    }
}
