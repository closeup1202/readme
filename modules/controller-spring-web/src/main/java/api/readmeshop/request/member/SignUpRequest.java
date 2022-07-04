package api.readmeshop.request.member;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class SignUpRequest {

    @NotBlank
    @Email
    private String useremail;

    @Min(8)
    @Max(16)
    @NotBlank
    private String userpassword;

    @Builder
    public SignUpRequest(String useremail, String userpassword) {
        this.useremail = useremail;
        this.userpassword = userpassword;
    }
}
