package io.github.kduferreira.trancaapi.trancaapi.infra.repository;


import io.github.kduferreira.trancaapi.trancaapi.domain.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatosRepository  extends JpaRepository<Contatos,Long> {




    Optional<Contatos> findByDocumento (String documento);
}
