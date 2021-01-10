package orangetalents.com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "email", length = 60, unique = true)
    private String email;

    @NotBlank
    @CPF
    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @JsonFormat(pattern="dd/MM/yyyy")
    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
