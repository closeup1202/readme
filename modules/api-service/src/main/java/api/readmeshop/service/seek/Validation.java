package api.readmeshop.service.seek;

import api.readmeshop.domain.user.member.Member;

public interface Validation {

    Member isWriter(String email);
}
