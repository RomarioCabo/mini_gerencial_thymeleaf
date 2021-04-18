package romario.cabo.com.br.mini_gerencial_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
