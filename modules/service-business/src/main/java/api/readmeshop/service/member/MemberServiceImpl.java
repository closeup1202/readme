package api.readmeshop.service.member;

import api.readmeshop.service.member.request.Required;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import readmeshop.serviceImpl.member.DeleteAccount;
import readmeshop.serviceImpl.member.SignUp;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final SignUp signUp;
    private final DeleteAccount deleteAccount;

    @Override
    public void signUp(Required required) {

    }
}
