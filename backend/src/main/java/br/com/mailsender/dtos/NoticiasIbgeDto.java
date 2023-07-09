package br.com.mailsender.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NoticiasIbgeDto {

    private List<Item> items = new ArrayList<>();

    @Data
    public static class Item {
        private String titulo;
        private String introducao;
        private String link;
        private String imagens;
        private String urlImagem;
    }

}
