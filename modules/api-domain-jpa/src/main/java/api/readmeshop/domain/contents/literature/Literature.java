package api.readmeshop.domain.contents.literature;

import api.readmeshop.domain.user.member.curator.Curator;
import api.readmeshop.domain.user.member.writer.Writer;
import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "genre")
@Getter
public abstract class Literature extends LiteratureHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "literatureId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerId")
    private Writer writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curatorId")
    private Curator curator;

    private String title;

    @Lob
    private String contents;

    public Literature(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    protected Literature() {}
}
