package romario.cabo.com.br.mini_gerencial_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByName(String name);

    @Query("SELECT position FROM Position position WHERE position.id=:id")
    Position findPositionById(Long id);
}
