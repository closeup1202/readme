package api.readmeshop.domain.user.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

     Optional<String> findByEmail(String email);
}
