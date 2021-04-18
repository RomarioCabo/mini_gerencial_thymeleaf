package romario.cabo.com.br.mini_gerencial_thymeleaf.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.enums.UF;

import javax.persistence.*;

@Entity
@Table(
        name = "enderecos",
        catalog = "mini_gerencial"
)
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractEntity<Long> {
    private static final long serialVersionUID = 6287532022795252796L;

    @Column(name = "logradouro", nullable = false)
    String  publicPlace;

    @Column(name = "bairro", nullable = false)
    String neighborhood;

    @Column(name = "cidade", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "numero", nullable = false)
    private Integer number;

    @Column(name = "complemento")
    private String complement;
}
