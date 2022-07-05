package api.readmeshop.web.request.member;

import api.readmeshop.service.member.request.SignUpRequired;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class SignUpRequest extends SignUpRequired{

    @NotBlank
    @Email
    private String useremail;

    @NotBlank
    private String username;

    @Min(8)
    @Max(16)
    @NotBlank
    private String userpassword;

    @Builder
    public SignUpRequest(SignUpRequest request){
        super(request.useremail, request.userpassword);
    }
}
