package api.readmeshop.domain.user.member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> getMemberList(int page);
}
