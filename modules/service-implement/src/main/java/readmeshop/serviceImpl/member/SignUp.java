package readmeshop.serviceImpl.member;

import api.readmeshop.domain.member.Member;
import api.readmeshop.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUp {

    private final MemberRepository memberRepository;
    private final Member member;

    public void signUp(){
        memberRepository.save(member);
    }

}
