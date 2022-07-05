package api.readmeshop.service.member;

import api.readmeshop.service.member.request.Required;

public interface MemberService<T extends Required> {

    void signUp(T t);
}
