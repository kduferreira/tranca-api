package io.github.kduferreira.trancaapi.trancaapi.infra.repository;

import io.github.kduferreira.trancaapi.trancaapi.domain.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {


    Optional<Motorista> findByPlaca(String placa);

}
