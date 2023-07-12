package br.com.mailsender.schedulers;

import br.com.mailsender.entities.Cliente;
import br.com.mailsender.repositories.ClienteRepository;
import br.com.mailsender.services.AgendadorEnvioEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgendadorEnvioEmail {

    private final AgendadorEnvioEmailService service;
    private final ClienteRepository clienteRepository;

    @Scheduled(cron = "0 0 8 * * *") //8:00 AM
    public void enviarEmails() {
        service.redefinirConteudoEmail();

        service.atualizarCorpoEmail();

        List<Cliente> clientes = clienteRepository.findAll();

        clientes.forEach(service::enviarEmail);
    }
}
