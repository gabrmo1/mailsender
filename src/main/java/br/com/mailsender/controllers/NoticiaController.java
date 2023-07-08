package br.com.mailsender.controllers;

import br.com.mailsender.dtos.NoticiaDto;
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

    @PostMapping("/cadastrar")
    public ResponseEntity<NoticiaDto> cadastrar(@RequestBody @Valid NoticiaDto noticiaDto) {
        return ResponseEntity.ok(service.criar(noticiaDto));
    }

    @GetMapping("/{oid}")
    public ResponseEntity<NoticiaDto> findByOid(@PathVariable("oid") String oid) {
        return ResponseEntity.ok(service.findById(oid));
    }

}
