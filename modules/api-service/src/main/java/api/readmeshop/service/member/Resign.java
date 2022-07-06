package api.readmeshop.service.member;

import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Resign {

    private final MemberRepository memberRepository;

    public void delete(Long memberId){
        memberRepository.deleteById(memberId);
    }
}
