package br.com.mailsender.controllers;

import br.com.mailsender.dtos.NoticiaDto;
import br.com.mailsender.services.AgendadorEnvioEmailService;
import br.com.mailsender.services.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService service;
    private final AgendadorEnvioEmailService agendadorEnvioEmailService;

    @PostMapping("/cadastrar")
    public ResponseEntity<NoticiaDto> cadastrar(@RequestBody @Valid NoticiaDto noticiaDto) {
        NoticiaDto noticiaDtoPosSave = service.criar(noticiaDto);

        agendadorEnvioEmailService.atualizarCorpoEmail(true);

        return ResponseEntity.ok(noticiaDtoPosSave);
    }

    @GetMapping("/{oid}")
    public ResponseEntity<NoticiaDto> findByOid(@PathVariable("oid") String oid) {
        return ResponseEntity.ok(service.findById(oid));
    }

}
