package api.readmeshop.domain.contents.literature;

import api.readmeshop.domain.user.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Literature extends LiteratureEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "literatureId")
    private Long id;

    //일대다 member 1 : literature  M
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private String title;

    @Lob
    private String contents;

    @Embedded
    private Shape shape;
}
