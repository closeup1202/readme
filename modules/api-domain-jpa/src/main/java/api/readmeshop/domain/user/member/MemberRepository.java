package api.readmeshop.domain.user.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

     //쿼리가 수행될 때 Lazy 조회가 아니고 Eager 조회로 authorities 정보를 같이 가져옴
     @EntityGraph(attributePaths = "authorities")
     Optional<Member> findByEmail(String email);
}
