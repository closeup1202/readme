package api.readmeshop.web.controller;

import api.readmeshop.service.member.MemberService;
import api.readmeshop.web.request.member.SignUpRequest;
import api.readmeshop.service.member.request.SignUpRequired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Validated SignUpRequest request){

        SignUpRequired signUpRequired = SignUpRequest.builder()
                                                     .request(request)
                                                     .build();
        memberService.signUp(signUpRequired);
    }


}
