package romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = -7286939065069239476L;

    private Long id;
    private String name;
    private Double salary;
    private LocalDate dateEntry;
    private LocalDate departureDate;

    private AddressDTO address;
    private PositionDTO position;
}
