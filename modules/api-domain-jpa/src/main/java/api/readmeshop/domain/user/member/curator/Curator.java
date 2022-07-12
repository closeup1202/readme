package api.readmeshop.domain.user.member.curator;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.user.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("C")
public class Curator extends Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curatorId")
    private Long id;

    @OneToMany(mappedBy = "curator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Literature> critics = new ArrayList<>();
}
