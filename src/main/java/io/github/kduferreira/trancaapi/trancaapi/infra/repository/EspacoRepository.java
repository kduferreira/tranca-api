package io.github.kduferreira.trancaapi.trancaapi.infra.repository;


import io.github.kduferreira.trancaapi.trancaapi.domain.Espaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacoRepository  extends JpaRepository<Espaco, Long> {
}
