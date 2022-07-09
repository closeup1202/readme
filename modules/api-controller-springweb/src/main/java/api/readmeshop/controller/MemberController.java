package api.readmeshop.controller;

import api.readmeshop.request.member.SignUpRequest;
import api.readmeshop.service.member.MemberService;
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

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest request){
        request.validate();
        memberService.signUp(new SignUpRequest(request));
    }

    @DeleteMapping("/resign-membership/{memberId}")
    public void resign(@PathVariable Long memberId){
        memberService.resign(memberId);
    }


}
