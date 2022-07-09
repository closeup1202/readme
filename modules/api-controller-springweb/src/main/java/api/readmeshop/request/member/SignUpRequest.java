package api.readmeshop.request.member;

import api.readmeshop.exception.ErrorCases;
import api.readmeshop.exception.ReadmeException;
import api.readmeshop.service.member.SignUpRequired;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static api.readmeshop.exception.ErrorCases.*;

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

    public void validate(){
        Pattern pattern = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = pattern.matcher(this.username);
        if (matcher.find()) {
            throw new ReadmeException(INVALID_REQUEST, "username", "특수문자는 포함할 수 없습니다");
        }
    }
}
