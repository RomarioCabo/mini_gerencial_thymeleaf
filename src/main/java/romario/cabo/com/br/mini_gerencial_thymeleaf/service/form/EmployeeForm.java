package romario.cabo.com.br.mini_gerencial_thymeleaf.service.form;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeeForm implements Serializable {
    private static final long serialVersionUID = 3549361618403616200L;

    private String name;
    private Double salary;
    private LocalDate dateEntry;
    private LocalDate departureDate;

    private Long idAddress;
    private Long idPosition;
}
