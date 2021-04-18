package romario.cabo.com.br.mini_gerencial_thymeleaf.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "cargos",
        catalog = "mini_gerencial",
        uniqueConstraints = @UniqueConstraint(columnNames = "nome")
)
@Data
@EqualsAndHashCode(callSuper = true)
public class Position extends AbstractEntity<Long> {
    private static final long serialVersionUID = 523121143587635222L;

    @Column(name = "nome", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
}
