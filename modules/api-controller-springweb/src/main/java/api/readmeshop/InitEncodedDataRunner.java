package api.readmeshop;

import api.readmeshop.request.user.member.SignUpRequest;
import api.readmeshop.service.user.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class InitEncodedDataRunner implements CommandLineRunner {

    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) {
            SignUpRequest request = SignUpRequest.builder()
                    .email("b@naver.com")
                    .password("b1234567!")
                    .username("유저임")
                    .build();
            memberService.signUp(request.getSignUpRequired());
    }
}
