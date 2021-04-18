package romario.cabo.com.br.mini_gerencial_thymeleaf.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 635202739566101055L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}
