package api.readmeshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import readmeshop.serviceImpl.member.DeleteAccount;
import readmeshop.serviceImpl.member.SignUp;
import readmeshop.serviceImpl.member.diversionPipe;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final SignUp signUp;
    private final DeleteAccount deleteAccount;
    private final diversionPipe diversionPipe;

}
