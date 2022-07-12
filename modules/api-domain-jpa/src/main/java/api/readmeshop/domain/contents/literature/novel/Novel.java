package api.readmeshop.domain.contents.literature.novel;

import api.readmeshop.domain.contents.literature.Literature;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("N")
public class Novel extends Literature {

    @Enumerated(EnumType.STRING)
    private NovelShape shape;
}
