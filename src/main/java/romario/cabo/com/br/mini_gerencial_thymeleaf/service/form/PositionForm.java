package romario.cabo.com.br.mini_gerencial_thymeleaf.service.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionForm implements Serializable {
    private static final long serialVersionUID = 7320162374347286165L;

    private String name;
    private Long idDepartment;
}
