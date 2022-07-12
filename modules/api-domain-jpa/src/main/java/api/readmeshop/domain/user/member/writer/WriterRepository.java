package api.readmeshop.domain.user.member.writer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    Optional<Writer> findByEmail(String email);
}
