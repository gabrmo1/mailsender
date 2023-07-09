package br.com.mailsender.util;

import br.com.mailsender.controllers.ClienteController;
import br.com.mailsender.dtos.ClienteDto;
import br.com.mailsender.entities.Cliente;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClienteUtils {

    protected Cliente criarEntidadeComDadosDoDTO(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());
        try {
            cliente.setDataNascimento(formatter.parse(clienteDto.getDataNascimento()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }

    protected ModelMapper configuredMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Cliente.class, ClienteDto.class).addMappings(mapping -> mapping.map(Cliente::getOid, ClienteDto::setChave));

        return mapper;
    }

    protected void implementFindByOidHATEOAS(ClienteDto clienteDto) {
        clienteDto.add(linkTo(methodOn(ClienteController.class).findByOid(clienteDto.getChave())).withSelfRel());
    }

}
