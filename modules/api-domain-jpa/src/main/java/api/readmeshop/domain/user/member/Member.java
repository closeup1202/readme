package api.readmeshop.domain.user.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "position")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    private String email;

    private String password;

    @Builder
    public Member(String useremail, String userpassword) {
        this.email = useremail;
        this.password = userpassword;
    }
}
