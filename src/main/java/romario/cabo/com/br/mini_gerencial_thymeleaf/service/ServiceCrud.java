package romario.cabo.com.br.mini_gerencial_thymeleaf.service;

import java.util.List;

public interface ServiceCrud<ENTITY> {

	void save(ENTITY object);

    void delete(Long id);

    List<ENTITY> findAll();

    ENTITY findById(Long id);
}
