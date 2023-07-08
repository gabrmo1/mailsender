package br.com.mailsender.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDto extends RepresentationModel<ClienteDto> {

    private String chave;

    @NotBlank(message = "O nome precisa ser informado")
    private String nome;

    @NotBlank(message = "o email precisa ser informado")
    @Email(message = "O formato do e-mail está incorreto")
    private String email;

    @Past
    @NotNull(message = "A data de nascimento precisa ser informada")
    private Date dataNascimento;

}
