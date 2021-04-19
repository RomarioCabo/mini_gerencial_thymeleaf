package romario.cabo.com.br.mini_gerencial_thymeleaf.service;

import java.util.List;

public interface ServiceCrud<DTO, FORM> {

    DTO save(FORM object, Long id);

    void delete(Long id);

    List<DTO> findAll();
}
