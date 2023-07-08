package br.com.mailsender.repositories;

import br.com.mailsender.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, String> {
}
