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
        LocalDate hoje = LocalDate.now();

        Date dataConsultaDate = java.sql.Date.valueOf(dataConsulta);
        Date hojeDate = java.sql.Date.valueOf(hoje);
        Query consulta = entityManager.createQuery("SELECT n FROM Noticia n WHERE n.creationDate BETWEEN :dataConsulta AND :hoje");

        consulta.setParameter("dataConsulta", dataConsultaDate)
                .setParameter("hoje", hojeDate);

        return consulta.getResultList();
    }

}
