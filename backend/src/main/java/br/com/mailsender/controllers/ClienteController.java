package br.com.mailsender.controllers;

import br.com.mailsender.dtos.ClienteDto;
import br.com.mailsender.entities.Cliente;
import br.com.mailsender.services.AgendadorEnvioEmailService;
import br.com.mailsender.services.ClienteService;
import br.com.mailsender.util.ClassMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;
    private final AgendadorEnvioEmailService agendadorEnvioEmailService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto clienteDto) {
        Cliente cliente = service.criar(clienteDto);
        ClienteDto clienteDtoPosPersist = ClassMapper.parseObject(service.configuredMapper(), cliente, ClienteDto.class);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> agendadorEnvioEmailService.enviarEmail(cliente));

        executorService.shutdown();

        service.implementFindByOidHATEOAS(clienteDtoPosPersist);

        return ResponseEntity.ok(clienteDtoPosPersist);
    }

    @GetMapping("/{oid}")
    public ResponseEntity<ClienteDto> findByOid(@PathVariable("oid") String oid) {
        return ResponseEntity.ok(service.findById(oid));
    }

}

