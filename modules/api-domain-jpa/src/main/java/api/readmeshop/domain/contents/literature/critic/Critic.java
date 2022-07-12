package api.readmeshop.domain.contents.literature.critic;

import api.readmeshop.domain.contents.literature.Literature;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("T")
public class Critic extends Literature {

    @Enumerated(EnumType.STRING)
    private CriticShape shape;
}
