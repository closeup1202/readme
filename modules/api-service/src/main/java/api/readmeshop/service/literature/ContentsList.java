package api.readmeshop.service.literature;

import api.readmeshop.domain.contents.literature.LiteratureEntity;
import api.readmeshop.service.policies.posting.PostedPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContentsList<T extends LiteratureEntity> {

    List<T> getList(PostedPolicy postedPolicy);
}
