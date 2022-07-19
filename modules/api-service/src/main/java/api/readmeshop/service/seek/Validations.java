package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.domain.user.member.MemberType;
import api.readmeshop.service.exception.ErrorCases;
import api.readmeshop.service.exception.ReadmeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static api.readmeshop.service.exception.ErrorCases.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class Validations implements Validation{

    private final MemberRepository memberRepository;

    @Override
    public Member isWriter(String email){
        Member member = isExistMemberEmail(email);

        if(member.getType() != MemberType.WRITER){
            throw new ReadmeException(NO_AUTHORITY);
        }
        return member;
    }

    @Override
    public void duplicatedEmailByMember(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(e -> new ReadmeException(NOTFOUND));
    }

    @Override
    public void isExistMemberId(Long id) {
        memberRepository.findById(id)
                .orElseThrow(() -> new ReadmeException(NOTFOUND));
    }

    @Override
    public Member isExistMemberEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new ReadmeException(NOTFOUND));
    }
}
