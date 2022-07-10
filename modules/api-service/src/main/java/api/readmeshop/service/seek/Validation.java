package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.Member;

public interface Validation {

    boolean isWriter(String email);
    Member savedMemberCheck(String email);
}
