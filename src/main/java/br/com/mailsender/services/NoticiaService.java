package br.com.mailsender.services;

import br.com.mailsender.dtos.NoticiaDto;
import br.com.mailsender.entities.Noticia;
import br.com.mailsender.repositories.NoticiaRepository;
import br.com.mailsender.util.ClassMapper;
import br.com.mailsender.util.NoticiaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticiaService extends NoticiaUtils {

    private final NoticiaRepository repository;

    @Transactional
    public NoticiaDto criar(NoticiaDto noticiaDto) {
        Noticia noticia = criarEntidadeComDadosDoDTO(noticiaDto);

        repository.save(noticia);

        NoticiaDto noticiaDtoPosSave = ClassMapper.parseObject(configuredMapper(), noticia, NoticiaDto.class);
        implementFindByOidHATEOAS(noticiaDtoPosSave);

        return noticiaDtoPosSave;
    }

    public NoticiaDto findById(String oid) {
        Noticia noticia = repository.findById(oid).orElseThrow();

        return ClassMapper.parseObject(configuredMapper(), noticia, NoticiaDto.class);
    }
}
