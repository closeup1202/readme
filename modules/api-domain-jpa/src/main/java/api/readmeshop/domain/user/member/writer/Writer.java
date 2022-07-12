package api.readmeshop.domain.user.member.writer;

import api.readmeshop.domain.contents.literature.Literature;
import api.readmeshop.domain.user.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("W")
public class Writer extends Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writerId")
    private Long id;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Literature> writings = new ArrayList<>();
}
