package readmeshop.serviceImpl.member;

import api.readmeshop.domain.controll.Duplication;
import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUp {

    private final MemberRepository memberRepository;
    private final Member member;
    private final Duplication duplication;

    public void save(String email){
        duplication.DuplicatedEmailByMember(email);
        Member savedMember = memberRepository.save(member);
    }

}
