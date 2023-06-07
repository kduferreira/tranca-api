package io.github.kduferreira.trancaapi.trancaapi.service;

import io.github.kduferreira.trancaapi.trancaapi.domain.Contatos;
import io.github.kduferreira.trancaapi.trancaapi.exception.NegocioException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.ContatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContatosService {


    private final ContatosRepository contatosRepository;


    public Contatos buscar (Long id){
        return contatosRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Contato nao existente."));
    }


    @Transactional
    public Contatos save (Contatos contatos) {
        boolean documentoEmUso = contatosRepository.findByDocumento(contatos.getDocumento())
                .stream()
                .anyMatch(ContatoExistente -> !ContatoExistente.equals(contatos));

        return contatosRepository.save(contatos);
    }

    @Transactional
    public void excluir(Long id) {
        contatosRepository.deleteById(id);
    }
 }
