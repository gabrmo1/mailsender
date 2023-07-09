package br.com.mailsender.controllers;

import br.com.mailsender.dtos.ClienteDto;
import br.com.mailsender.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto clienteDto) {
        return ResponseEntity.ok(service.criar(clienteDto));
    }

    @GetMapping("/{oid}")
    public ResponseEntity<ClienteDto> findByOid(@PathVariable("oid") String oid) {
        return ResponseEntity.ok(service.findById(oid));
    }

}

