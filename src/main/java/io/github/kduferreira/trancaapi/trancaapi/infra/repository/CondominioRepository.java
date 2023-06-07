package io.github.kduferreira.trancaapi.trancaapi.infra.repository;


import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominioRepository  extends JpaRepository<Condominio, Long> {
    Optional<Condominio> findByCnpj(String cnpj);

}
