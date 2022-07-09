package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Duplications implements Duplication{
    
    private final MemberRepository memberRepository;

    @Override
    public void DuplicatedEmailByMember(String email) {
        memberRepository.findByEmail(email)
                        .ifPresent(RuntimeException::new);
    }
}
