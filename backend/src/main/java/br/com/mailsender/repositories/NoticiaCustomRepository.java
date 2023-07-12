package br.com.mailsender.repositories;

import br.com.mailsender.entities.Noticia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class NoticiaCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Noticia> obterNoticiasNewsletter() {
        LocalDate dataConsulta = LocalDate.now().minusDays(1);

        Date dataConsultaDate = java.sql.Date.valueOf(dataConsulta);
        Query consulta = entityManager.createQuery("SELECT n FROM Noticia n WHERE n.creationDate > :dataConsulta");

        consulta.setParameter("dataConsulta", dataConsultaDate);

        return consulta.getResultList();
    }

}
