package api.readmeshop.domain.user.member;

import api.readmeshop.domain.History;
import api.readmeshop.domain.contents.literature.Literature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends History {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    @Column(unique = true)
    private String email;

    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @JsonIgnore
    private boolean activated;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Literature> literatures = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = {@JoinColumn(name = "memberId", referencedColumnName = "memberId")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authenticationProvider;

    @Builder
    public Member(String email, String password, String username, MemberType type, Set<Authority> authorities, boolean activated) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
        this.authorities = authorities;
        this.activated = activated;
    }

    @Builder(builderMethodName = "defaultMember")
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
