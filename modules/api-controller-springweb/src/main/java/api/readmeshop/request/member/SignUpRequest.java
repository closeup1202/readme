package api.readmeshop.request.member;

import api.readmeshop.service.member.SignUpRequired;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class SignUpRequest extends SignUpRequired {

    @Email
    @NotBlank
    private String useremail;

    @NotBlank
    private String username;

    @NotBlank
    private String userpassword;

    @Builder
    public SignUpRequest(String useremail, String username, String userpassword) {
        super();
        this.useremail = useremail;
        this.username = username;
        this.userpassword = userpassword;
    }

    public SignUpRequest(SignUpRequest request){
        super(request.useremail, request.userpassword);
    }
}
