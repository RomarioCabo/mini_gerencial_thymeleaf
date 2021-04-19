package romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDTO implements Serializable {
    private static final long serialVersionUID = 3762883533526152217L;

    private Long id;
    private String name;
}
