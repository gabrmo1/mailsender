package br.com.mailsender.util;

import br.com.mailsender.controllers.NoticiaController;
import br.com.mailsender.dtos.NoticiaDto;
import br.com.mailsender.entities.Noticia;
import org.modelmapper.ModelMapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class NoticiaUtils {

    protected Noticia criarEntidadeComDadosDoDTO(NoticiaDto noticiaDto) {
        Noticia noticia = new Noticia();

        noticia.setTitulo(noticiaDto.getTitulo());
        noticia.setDescricao(noticiaDto.getDescricao());
        noticia.setLink(noticiaDto.getLink());
        noticia.setUrlImagem(noticiaDto.getUrlImagem());

        return noticia;
    }

    protected ModelMapper configuredMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Noticia.class, NoticiaDto.class).addMappings(mapping -> mapping.map(Noticia::getOid, NoticiaDto::setChave));

        return mapper;
    }

    protected void implementFindByOidHATEOAS(NoticiaDto noticiaDto) {
        noticiaDto.add(linkTo(methodOn(NoticiaController.class).findByOid(noticiaDto.getChave())).withSelfRel());
    }

}
