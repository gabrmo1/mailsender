package br.com.mailsender.singleton;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class NoticiasDiariasSingleton {

    private String cabecalho;
    private String cabecalhoAniversariante;
    private String conteudoEmail;
    private String conteudoEmailAniversariante;
    private List<NoticiaDiaria> noticiasDiariaIbge = new ArrayList<>();

    @Data
    public static class NoticiaDiaria {
        private String titulo;
        private String descricao;
        private String link;
        private String urlImagem;
    }

}
