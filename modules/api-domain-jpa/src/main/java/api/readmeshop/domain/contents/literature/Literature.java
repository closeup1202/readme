package api.readmeshop.domain.contents.literature;

import api.readmeshop.domain.History;
import api.readmeshop.domain.user.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "genre")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Literature extends History {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "literatureId")
    private Long id;

    private String title;

    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    protected Literature(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }
}
