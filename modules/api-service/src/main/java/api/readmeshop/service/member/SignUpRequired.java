package api.readmeshop.service.member;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class SignUpRequired {

    private String useremail;
    private String userpassword;
}
