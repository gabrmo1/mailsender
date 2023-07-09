package br.com.mailsender.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "noticia")
@EqualsAndHashCode(callSuper = true)
public class Noticia extends DefaultEntityImpl {

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String link;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String urlImagem;

}
