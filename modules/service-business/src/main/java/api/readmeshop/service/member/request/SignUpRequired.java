package api.readmeshop.service.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class SignUpRequired extends Required{

    private String useremail;
    private String userpassword;
}
