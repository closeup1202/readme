package api.readmeshop.request.user.member;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SignInRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
