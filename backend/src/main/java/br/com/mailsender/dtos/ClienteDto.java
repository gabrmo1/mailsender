package br.com.mailsender.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDto extends RepresentationModel<ClienteDto> {

    private String chave;

    @NotBlank(message = "O nome precisa ser informado")
    @Length(max = 100, message = "O nome pode ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "o email precisa ser informado")
    @Email(message = "O formato do e-mail está incorreto")
    @Length(max = 150, message = "O email pode ter no máximo 150 caracteres")
    private String email;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Formato de data inválido. Use o formato yyyy-MM-dd")
    @NotBlank(message = "A data de nascimento precisa ser informada")
    private String dataNascimento;

}
