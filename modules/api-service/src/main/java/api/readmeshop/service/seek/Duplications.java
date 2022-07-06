package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Duplications implements Duplication{
    
    private final MemberRepository memberRepository;

    @Override
    public String DuplicatedEmailByMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
    }
}
