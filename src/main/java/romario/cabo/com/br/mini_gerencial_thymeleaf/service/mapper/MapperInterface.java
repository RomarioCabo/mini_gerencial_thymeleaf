package romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper;

import javax.persistence.Tuple;
import java.util.List;

public interface MapperInterface<ENTITY, DTO, FORM> {

    ENTITY toEntity(FORM form);

    List<ENTITY> toEntity(List<FORM> forms);

    DTO toDto(ENTITY entity);

    List<DTO> toDto(List<ENTITY> entities);

    List<DTO> tupleToDto(List<Tuple> tuples);
}
