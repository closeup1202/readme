package api.readmeshop.service.user.member;

import api.readmeshop.service.seek.Duplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Duplication duplication;
    private final SignUp signUp;
    private final Resign resign;

    public void signUp(SignUpRequired required){
        duplication.DuplicatedEmailByMember(required.getEmail());
        signUp.save(required);
    };

    public void resign(Long memberId){
        resign.delete(memberId);
    };
}
