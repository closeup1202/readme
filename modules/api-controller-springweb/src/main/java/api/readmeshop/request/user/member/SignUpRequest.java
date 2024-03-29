package api.readmeshop.request.user.member;

import api.readmeshop.service.exception.ReadmeException;
import api.readmeshop.service.user.member.SignUpRequired;
import lombok.*;

import javax.validation.constraints.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static api.readmeshop.service.exception.ErrorCases.*;
import static api.readmeshop.service.user.member.SignUpRole.ADMIN;
import static api.readmeshop.service.user.member.SignUpRole.USER;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class SignUpRequest {

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 16)
    private String password;

    public SignUpRequired getSignUpRequired(){
        return SignUpRequired.builder()
                .email(this.email)
                .username(this.username)
                .password(this.password)
                .role( this.email.equals("admin") ? ADMIN.getRole() : USER.getRole() )
                .build();
    }

    public void validate(){
        Pattern pattern = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = pattern.matcher(this.username);
        if (matcher.find()) {
            throw new ReadmeException(INVALID_REQUEST, "username", "특수문자는 포함할 수 없습니다");
        }
    }
}
