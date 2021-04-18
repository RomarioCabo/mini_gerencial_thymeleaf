package romario.cabo.com.br.mini_gerencial_thymeleaf.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "funcionarios",
        catalog = "mini_gerencial",
        uniqueConstraints = @UniqueConstraint(columnNames = "nome")
)
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractEntity<Long> {
    private static final long serialVersionUID = -1515892298808667554L;

    @Column(name = "nome", nullable = false, unique = true)
    private String name;

    @Column(name = "salario", nullable = false, precision = 12, scale = 2)
    private Double salary;

    @Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
    private LocalDate dateEntry;

    @Column(name = "data_saida", columnDefinition = "DATE")
    private LocalDate departureDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private Position position;
}
