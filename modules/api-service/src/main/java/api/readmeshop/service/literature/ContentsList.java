package api.readmeshop.service.literature;

import api.readmeshop.domain.History;
import api.readmeshop.service.policies.posting.PostedPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContentsList<T extends History> {

    List<T> getList(PostedPolicy postedPolicy);
}
