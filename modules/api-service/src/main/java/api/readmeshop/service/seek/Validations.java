package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.Member;
import api.readmeshop.domain.user.member.MemberDevice;
import api.readmeshop.domain.user.member.MemberRepository;
import api.readmeshop.service.exception.ReadmeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static api.readmeshop.service.exception.ErrorCases.*;

@RequiredArgsConstructor
@Component
public class Validations implements Validation{

    private final MemberRepository memberRepository;

    @Override
    public boolean isWriter(String email) {
        Member member = savedMemberCheck(email);
        return member.getDevice() == MemberDevice.WRITER;
    }

    @Override
    public Member savedMemberCheck(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new ReadmeException(NOTFOUND));
    }
}
