package io.github.kduferreira.trancaapi.trancaapi.service;


import io.github.kduferreira.trancaapi.trancaapi.domain.Motorista;
import io.github.kduferreira.trancaapi.trancaapi.exception.NegocioException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;



    public Motorista buscar (Long id) {
        return motoristaRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Motorista nao cadastrado."));
    }


    @Transactional
    public Motorista save (Motorista motorista) {
        boolean placaEmUso = motoristaRepository.findByPlaca(motorista.getPlaca())
                .stream()
                .anyMatch(motoristaExistente -> !motoristaExistente.equals(motorista));

    if(placaEmUso) {
        throw new NegocioException("Motorista ja cadastrado com essa placa.");
    }


    return motoristaRepository.save(motorista);
    }

    @Transactional
    public void excluir (Long id) {
    motoristaRepository.deleteById(id);
    }


}
