package romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionDTO implements Serializable {
    private static final long serialVersionUID = 6543052247040897438L;

    private Long id;
    private String name;
    private DepartmentDTO department;
}
