package orangetalents.com.dto.response.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaRequest {

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @CPF
    @NotBlank(message = "CPF é obrigatorio")
    private String cpf;

    @Email(message = "Formato de email incorreto")
    @NotBlank(message = "Email é obrigatorio")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatorio")
    private LocalDate dataNascimento;
}
