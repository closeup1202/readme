package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.service.exception.ErrorCases;
import api.readmeshop.service.exception.ReadmeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Duplications implements Duplication{
    
    private final MemberRepository memberRepository;

    @Override
    public void DuplicatedEmailByMember(String email) {
        memberRepository.findByEmail(email)
                        .ifPresent(e -> {new ReadmeException(ErrorCases.NOTFOUND); log.info("e : {}", e);});
    }
}
