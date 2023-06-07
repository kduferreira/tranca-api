package io.github.kduferreira.trancaapi.trancaapi.infra.repository;

import io.github.kduferreira.trancaapi.trancaapi.domain.Convites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public interface ConvitesRepository extends JpaRepository<Convites, Long> {
    Optional<Convites> findByDocumento(String documento);
}
