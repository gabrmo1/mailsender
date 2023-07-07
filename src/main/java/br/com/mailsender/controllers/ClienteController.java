package br.com.mailsender.controllers;

import br.com.mailsender.dtos.ClienteDto;
import br.com.mailsender.entities.Cliente;
import br.com.mailsender.repositories.ClienteRepository;
import br.com.mailsender.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;
    private final ClienteRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto clienteDto) {
        ClienteDto clienteDtoPosSave = service.criar(clienteDto);

        return ResponseEntity.ok(clienteDtoPosSave);
    }

    @GetMapping(value = "/{oid}")
    public ResponseEntity<Cliente> findByOid(@PathVariable("oid") String oid) {
        Cliente cliente = repository.findById(oid).orElseThrow();

        return ResponseEntity.ok(cliente);
    }

}
