package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.LiteratureHistory;
import api.readmeshop.service.policies.posting.PostedPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContentsList<T extends LiteratureHistory> {

    List<T> getList(PostedPolicy postedPolicy);
}
