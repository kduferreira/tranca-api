package io.github.kduferreira.trancaapi.trancaapi.service;

import io.github.kduferreira.trancaapi.trancaapi.domain.Convites;
import io.github.kduferreira.trancaapi.trancaapi.exception.NegocioException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.ConvitesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ConvitesService {


    private final ConvitesRepository convitesRepository;



    public Convites buscar (Long id) {
        return convitesRepository.findById(id)
        .orElseThrow(() -> new NegocioException("Convite inexistente."));
    }

    @Transactional
    public Convites save (Convites convites) {
        boolean documentoEmUso = convitesRepository.findByDocumento(convites.getDocumento())
                .stream()
                .anyMatch(convitesExistente -> !convitesExistente.equals(convites));

        convites.setDt_criacao(OffsetDateTime.now());
        return convitesRepository.save(convites);

    }

    @Transactional
    public void excluir (Long id){
         convitesRepository.deleteById(id);
    }
}
