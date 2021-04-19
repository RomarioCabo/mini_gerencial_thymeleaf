package romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 2606328491809231503L;

    private Long id;
    private String publicPlace;
    private String neighborhood;
    private String city;
    private String uf;
    private String cep;
    private Integer number;
    private String complement;
}
