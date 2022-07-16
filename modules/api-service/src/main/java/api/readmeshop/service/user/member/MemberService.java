package api.readmeshop.service.user.member;

import api.readmeshop.service.seek.Duplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Duplication duplication;
    private final SignUp signUp;
    private final Resign resign;

    public void signUp(SignUpRequired required){
        duplication.DuplicatedEmailByMember(required.getUseremail());
        signUp.save(required);
    };

    public void resign(Long memberId){
        resign.delete(memberId);
    };
}
