package api.readmeshop.service.member;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUp {

    private final MemberRepository memberRepository;

    public void save(SignUpRequired required) {
        Member member = Member.builder()
                .useremail(required.getUseremail())
                .userpassword(required.getUserpassword())
                .build();

        memberRepository.save(member);
    }
}
