package readmeshop.serviceImpl.member;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccount {

    private final MemberRepository memberRepository;
    private final Member member;

    public void deleteAccount(){
        memberRepository.delete(member);
    }
}
