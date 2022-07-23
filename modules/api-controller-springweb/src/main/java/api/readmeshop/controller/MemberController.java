package api.readmeshop.controller;

import api.readmeshop.aop.Trace;
import api.readmeshop.request.user.member.SignUpRequest;
import api.readmeshop.service.user.member.MemberService;
import api.readmeshop.service.user.member.SignUpRequired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public String hello(){
        return "Hello";
    }

    @Trace
    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest request){
        request.validate();
        log.info("clear?");
        SignUpRequired required = request.getSignUpRequired();
        memberService.signUp(required);
    }

    @DeleteMapping("/resign/{memberId}")
    public void resign(@PathVariable Long memberId){
        memberService.resign(memberId);
    }


}
