package io.github.kduferreira.trancaapi.trancaapi.service;


import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.exception.NegocioException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class  CondominioService {


    private final CondominioRepository condominioRepository;


    public Condominio buscar (Long id) {

        return condominioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Condominio não existe."));

    }

    @Transactional
    public Condominio salvar (Condominio condominio) {
        boolean cnpjEmUso = condominioRepository.findByCnpj(condominio.getCnpj())
                .stream()
                .anyMatch(CondominioExistente -> !CondominioExistente.equals(condominio));


        if(cnpjEmUso) {

            throw new NegocioException("Ja existe um condominio cadastrado com esse cnpj.");
        }
        return condominioRepository.save(condominio);
    }

    @Transactional
    public void excluir(Long id) {
        condominioRepository.deleteById(id);
    }

}
