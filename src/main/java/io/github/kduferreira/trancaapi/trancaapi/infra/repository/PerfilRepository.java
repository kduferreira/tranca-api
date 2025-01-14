package io.github.kduferreira.trancaapi.trancaapi.infra.repository;

import io.github.kduferreira.trancaapi.trancaapi.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(String nome);



}
