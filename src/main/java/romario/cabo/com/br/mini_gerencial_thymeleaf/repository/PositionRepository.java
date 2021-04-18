package romario.cabo.com.br.mini_gerencial_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

}
