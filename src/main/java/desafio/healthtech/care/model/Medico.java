package desafio.healthtech.care.model;

import desafio.healthtech.care.enums.EspecialidadeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private EspecialidadeEnum especialidade;

    boolean ativo = true;
}
