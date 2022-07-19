package api.readmeshop.service.user.member;

import api.readmeshop.service.seek.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Validation validation;
    private final SignUp signUp;
    private final Resign resign;

    public void signUp(SignUpRequired required){
        validation.duplicatedEmailByMember(required.getEmail());
        signUp.save(required);
    };

    public void resign(Long memberId){
        validation.isExistMemberId(memberId);
        resign.delete(memberId);
    };
}
