package api.readmeshop.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class UserSignupDTO {

    private String useremail;
    private String userpassword;

    @Builder
    public UserSignupDTO(String useremail, String userpassword) {
        this.useremail = useremail;
        this.userpassword = userpassword;
    }
}
