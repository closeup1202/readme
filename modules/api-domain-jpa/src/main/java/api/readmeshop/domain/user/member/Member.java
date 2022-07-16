package api.readmeshop.domain.user.member;

import api.readmeshop.domain.History;
import api.readmeshop.domain.contents.literature.Literature;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends History {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Literature> literatures = new ArrayList<>();

    @Builder
    public Member(String email, String password, MemberType type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    @Builder(builderMethodName = "defaultMember")
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
