package br.com.mailsender.services;

import br.com.mailsender.dtos.ClienteDto;
import br.com.mailsender.entities.Cliente;
import br.com.mailsender.exceptions.DadosInvalidosException;
import br.com.mailsender.repositories.ClienteRepository;
import br.com.mailsender.util.ClassMapper;
import br.com.mailsender.util.ClienteUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService extends ClienteUtils {

    private final ClienteRepository repository;

    @Transactional
    public ClienteDto criar(ClienteDto clienteDto) {
        Cliente cliente = criarEntidadeComDadosDoDTO(clienteDto);

        boolean emailJaCadastrado = repository.existsClienteByEmail(cliente.getEmail());

        if (emailJaCadastrado) {
            throw new DadosInvalidosException("Este e-mail já está cadastrado");
        }

        repository.save(cliente);

        ClienteDto clienteDtoPosSave = ClassMapper.parseObject(configuredMapper(), cliente, ClienteDto.class);
        implementFindByOidHATEOAS(clienteDtoPosSave);

        return clienteDtoPosSave;
    }

    public ClienteDto findById(String oid) {
        Cliente cliente = repository.findById(oid).orElseThrow();

        return ClassMapper.parseObject(configuredMapper(), cliente, ClienteDto.class);
    }
}
