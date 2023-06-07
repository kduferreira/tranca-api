package io.github.kduferreira.trancaapi.trancaapi.service;

import io.github.kduferreira.trancaapi.trancaapi.domain.Espaco;
import io.github.kduferreira.trancaapi.trancaapi.exception.NegocioException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.EspacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EspacoService {


    private final EspacoRepository espacoRepository;


    public Espaco buscarEspaco (Long id) {

        return espacoRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Espaco nao existente."));
    }

    @Transactional
    public  Espaco save(Espaco espaco) {
        return espacoRepository.save(espaco);

    }


    @Transactional
    public void excluir(Long id) {
        espacoRepository.deleteById(id);
    }
    }

