package br.com.mailsender.services;

import br.com.mailsender.singleton.NoticiasDiariasSingleton;
import br.com.mailsender.dtos.NoticiasIbgeDto;
import br.com.mailsender.entities.Cliente;
import br.com.mailsender.entities.Noticia;
import br.com.mailsender.repositories.NoticiaCustomRepository;
import br.com.mailsender.util.AgendadorEnvioEmailUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendadorEnvioEmailService extends AgendadorEnvioEmailUtils {

    private final RestTemplate restTemplate;
    private final JavaMailSender mailSender;
    private final NoticiaCustomRepository noticiaRepository;
    private final NoticiasDiariasSingleton noticiasDiariasSingleton;

    @PostConstruct
    public void definirEstruturaInicialEmail() {
        noticiasDiariasSingleton.setCabecalho(criarCabecalho());
        noticiasDiariasSingleton.setCabecalhoAniversariante(criarCabecalhoAniversariante());

        atualizarCorpoEmail();
    }

    public NoticiasIbgeDto obterNoticiasIbge() {
        String dataConsulta = obterDataConsulta();
        String url = "https://servicodados.ibge.gov.br/api/v3/noticias/?de=" + dataConsulta;

        NoticiasIbgeDto noticiasIBGEDto = null;

        ResponseEntity<NoticiasIbgeDto> response = restTemplate.getForEntity(url, NoticiasIbgeDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            noticiasIBGEDto = response.getBody();

            if (noticiasIBGEDto != null && !noticiasIBGEDto.getItems().isEmpty()) {

                for (NoticiasIbgeDto.Item item : noticiasIBGEDto.getItems()) {
                    Gson gson = new Gson();
                    String json = item.getImagens();
                    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                    String imageIntro = jsonObject.get("image_intro").getAsString();
                    item.setUrlImagem("https://agenciadenoticias.ibge.gov.br//" + imageIntro);
                }

            }

        }

        return noticiasIBGEDto;
    }

    public void enviarEmail(Cliente cliente) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        LocalDate dataNascimento = cliente.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoje = LocalDate.now();

        boolean aniversarioHoje = dataNascimento.getDayOfMonth() == hoje.getDayOfMonth()
                && dataNascimento.getMonth() == hoje.getMonth();

        try {
            helper.setTo(cliente.getEmail());
            helper.setSubject("Notícias do dia");
            helper.setText(aniversarioHoje ? noticiasDiariasSingleton.getConteudoEmailAniversariante() : noticiasDiariasSingleton.getConteudoEmail(), true);

            mailSender.send(message);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }

    public void redefinirConteudoEmail() {
        noticiasDiariasSingleton.setNoticiasDiariaIbge(new ArrayList<>());
        noticiasDiariasSingleton.setConteudoEmail("");
        noticiasDiariasSingleton.setConteudoEmailAniversariante("");
    }

    public void atualizarCorpoEmail() {
        atualizarCorpoEmail(false);
    }

    public void atualizarCorpoEmail(boolean novaNoticia) {
        List<Noticia> noticiasNewsletter = noticiaRepository.obterNoticiasNewsletter();

        if (!novaNoticia || noticiasDiariasSingleton.getNoticiasDiariaIbge().isEmpty()) {
            List<NoticiasDiariasSingleton.NoticiaDiaria> noticiasDiariasIbge = noticiasDiariasSingleton.getNoticiasDiariaIbge();

            NoticiasIbgeDto noticiasIbgeDto = obterNoticiasIbge();

            for (NoticiasIbgeDto.Item item : noticiasIbgeDto.getItems()) {
                NoticiasDiariasSingleton.NoticiaDiaria novaNoticiaIbge = new NoticiasDiariasSingleton.NoticiaDiaria();

                novaNoticiaIbge.setTitulo(item.getTitulo());
                novaNoticiaIbge.setDescricao(item.getIntroducao());
                novaNoticiaIbge.setLink(item.getLink());
                novaNoticiaIbge.setUrlImagem(item.getUrlImagem());

                noticiasDiariasIbge.add(novaNoticiaIbge);
            }

            noticiasDiariasSingleton.setNoticiasDiariaIbge(noticiasDiariasIbge);
        }

        String corpoEmail = criarCorpoEmail(noticiasDiariasSingleton.getNoticiasDiariaIbge(), noticiasNewsletter);

        noticiasDiariasSingleton.setConteudoEmail(criarConteudoEmail(noticiasDiariasSingleton.getCabecalho(), corpoEmail));
        noticiasDiariasSingleton.setConteudoEmailAniversariante(criarConteudoEmail(noticiasDiariasSingleton.getCabecalhoAniversariante(), corpoEmail));
    }

}
