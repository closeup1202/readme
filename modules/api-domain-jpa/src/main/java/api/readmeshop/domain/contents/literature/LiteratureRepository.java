package api.readmeshop.domain.contents.literature;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteratureRepository extends JpaRepository<Literature, Long>, LiteratureRepositoryCustom {

}
