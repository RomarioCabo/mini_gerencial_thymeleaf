package romario.cabo.com.br.mini_gerencial_thymeleaf.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "departamentos",
        catalog = "mini_gerencial",
        uniqueConstraints = @UniqueConstraint(columnNames = "nome")
)
@Data
@EqualsAndHashCode(callSuper = true)
public class Department extends AbstractEntity<Long> {
    private static final long serialVersionUID = 4909846296262444400L;

    @Column(name = "nome", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Position> positions = new ArrayList<>();
}
