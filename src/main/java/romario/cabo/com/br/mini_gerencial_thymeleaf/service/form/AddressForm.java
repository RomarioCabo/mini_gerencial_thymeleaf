package romario.cabo.com.br.mini_gerencial_thymeleaf.service.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressForm implements Serializable {
    private static final long serialVersionUID = 155075604431165357L;

    private String publicPlace;
    private String neighborhood;
    private String city;
    private String uf;
    private String cep;
    private Integer number;
    private String complement;
}
