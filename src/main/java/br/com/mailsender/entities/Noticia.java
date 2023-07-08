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

    @Column(length = 50)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String link;

}


