package br.com.mailsender.controllers;

import br.com.mailsender.dtos.ClienteDto;
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

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto clienteDto) {
        ClienteDto clienteDtoPosSave = service.criar(clienteDto);

        return ResponseEntity.ok(clienteDtoPosSave);
    }

    @GetMapping(value = "/{oid}")
    public ResponseEntity<ClienteDto> findByOid(@PathVariable("oid") String oid) {
        ClienteDto clienteDto = service.findById(oid);

        return ResponseEntity.ok(clienteDto);
    }

}
