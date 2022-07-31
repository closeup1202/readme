package api.readmeshop.controller;

import api.readmeshop.aop.Trace;
import api.readmeshop.jwt.JwtHelper;
import api.readmeshop.request.user.member.SignInRequest;
import api.readmeshop.request.user.member.SignUpRequest;
import api.readmeshop.request.user.member.TokenResponse;
import api.readmeshop.service.user.member.MemberService;
import api.readmeshop.service.user.member.SignUpRequired;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtHelper jwtHelper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final HttpHeaders httpHeaders;

    @GetMapping("/test")
    public String hello(){
        return "Hello";
    }

    @Trace
    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest request){
        request.validate();
        SignUpRequired required = request.getSignUpRequired();
        memberService.signUp(required);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signin(@RequestBody @Valid SignInRequest request){
        String jwtToken = getJwtToken(request);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer" + jwtToken);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(new TokenResponse(jwtToken));
    }

    private String getJwtToken(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()); //어쎈티케이트 토큰 생성
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken); //authenticate 메소드가 실행이 될 떄 MemberDetailsService 의 loadUserByUsername 가 실행이 됨.
        SecurityContextHolder.getContext().setAuthentication(authenticate); //이 authenticate 객체를 시큐리티컨텍스트에 저장
        return jwtHelper.createJwtToken(authenticate); // authenticate 객체로 토큰 생성
    }

    @DeleteMapping("/resign/{memberId}")
    public void resign(@PathVariable Long memberId){
        memberService.resign(memberId);
    }


}
