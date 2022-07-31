package api.readmeshop.service.user.member;

import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SignUpRequired {

    private String email;
    private String username;
    private String password;
    private String role;
}
