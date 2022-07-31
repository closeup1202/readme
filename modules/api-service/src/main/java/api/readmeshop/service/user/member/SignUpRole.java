package api.readmeshop.service.user.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignUpRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String role;
}
