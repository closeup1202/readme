package api.readmeshop.domain.user.member;

import api.readmeshop.domain.contents.literature.Literature;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    private String email;

    private String password;

    @OneToMany
    @JoinColumn(name = "memberId")
    private List<Literature> writings = new ArrayList<>();

    private MemberDevice device;

    @Builder
    public Member(String useremail, String userpassword) {
        this.email = useremail;
        this.password = userpassword;
    }
}
