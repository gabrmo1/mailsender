package br.com.mailsender.schedulers;

import br.com.mailsender.dtos.NoticiasIbgeDto;
import br.com.mailsender.entities.Cliente;
import br.com.mailsender.entities.Noticia;
import br.com.mailsender.repositories.ClienteRepository;
import br.com.mailsender.repositories.NoticiaCustomRepository;
import br.com.mailsender.services.AgendadorEnvioEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AgendadorEnvioEmail {

    private final JavaMailSender mailSender;
    private final AgendadorEnvioEmailService service;
    private final ClienteRepository clienteRepository;
    private final NoticiaCustomRepository noticiaRepository;

    @Scheduled(cron = "0 0 8 * * *") //8:00 AM
    public void enviarEmails() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        NoticiasIbgeDto noticiasIbgeDto = service.obterNoticiasIbge();
        List<Noticia> noticias = noticiaRepository.obterNoticiasNewsletter();

        String cabecalho = service.criarCabecalho();
        String cabecalhoAniversariante = service.criarCabecalhoAniversariante();

        String corpoEmail = service.criarCorpoEmail(noticiasIbgeDto.getItems(), noticias);

        String conteudoEmail = service.criarConteudoEmail(cabecalho, corpoEmail);
        String conteudoEmailAniversariante = service.criarConteudoEmail(cabecalhoAniversariante, corpoEmail);

        List<Cliente> clientes = clienteRepository.findAll();

        LocalDate hoje = LocalDate.now();
        for (Cliente cliente : clientes) {
            try {
                LocalDate dataNascimento = cliente.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                boolean aniversarioHoje = dataNascimento.getDayOfMonth() == hoje.getDayOfMonth()
                        && dataNascimento.getMonth() == hoje.getMonth();

                helper.setTo(cliente.getEmail());
                helper.setSubject("Notícias do dia!");

                if (aniversarioHoje) {
                    helper.setText(conteudoEmailAniversariante, true);
                } else {
                    helper.setText(conteudoEmail, true);
                }

                mailSender.send(message);
                System.out.println("E-mail enviado com sucesso!");
            } catch (MessagingException e) {
                System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
            }
        }
    }
}
