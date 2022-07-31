package api.readmeshop.service.user.member;

import api.readmeshop.domain.user.member.Authority;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class SignUp {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(SignUpRequired required) {
        Member member = Member.builder()
                .email(required.getEmail())
                .password(passwordEncoder.encode(required.getPassword()))
                .username(required.getUsername())
                .activated(true)
                .authorities(Collections.singleton(saveAuthority(required)))
                .build();

        memberRepository.save(member);
    }

    private Authority saveAuthority(SignUpRequired required){
        return Authority.builder().authorityName(required.getRole()).build();
    }
}
