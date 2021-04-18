package romario.cabo.com.br.mini_gerencial_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
