package api.readmeshop.controller;

import api.readmeshop.dto.user.UserSignupDTO;
import api.readmeshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/test")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Validated UserSignupDTO userSignupDTO){
        accountService.signup();
    }

}
