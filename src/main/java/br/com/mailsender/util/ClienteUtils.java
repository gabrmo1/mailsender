package br.com.mailsender.util;

import br.com.mailsender.controllers.ClienteController;
import br.com.mailsender.dtos.ClienteDto;
import br.com.mailsender.entities.Cliente;
import org.modelmapper.ModelMapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
public class ClienteUtils {

    protected Cliente criarEntidadeComDadosDoDTO(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setDataNascimento(clienteDto.getDataNascimento());

        return cliente;
    }

    protected ModelMapper configuredMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Cliente.class, ClienteDto.class).addMappings(mapping -> mapping.map(Cliente::getOid, ClienteDto::setOid));

        return mapper;
    }

    protected void implementFindByOidHATEOAS(ClienteDto clienteDto) {
        clienteDto.add(linkTo(methodOn(ClienteController.class).findByOid(clienteDto.getOid())).withSelfRel());
    }

}
