package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.Member;

public interface Validation {

    Member isWriter(String email);

    void duplicatedEmailByMember(String email);

    void isExistMemberId(Long id);

    Member isExistMemberEmail(String email);
}
