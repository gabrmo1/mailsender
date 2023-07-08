package br.com.mailsender.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoticiaDto extends RepresentationModel<NoticiaDto> {

    private String chave;

    @NotBlank(message = "O título precisa ser informado")
    private String titulo;

    @NotBlank(message = "A descrição precisa ser informada")
    private String descricao;

    @NotBlank(message = "O link precisa ser informado")
    private String link;

}
