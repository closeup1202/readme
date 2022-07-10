package api.readmeshop.service.policies.posting;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostedPolicy {

    private static final int MAX_SIZE = 3000;

    private Integer page = 1;
    private Integer size = 10;

    public Long getOffset() {
        return (long) (Math.max(1, page) -1 ) * Math.min(size, MAX_SIZE);
    }
}
