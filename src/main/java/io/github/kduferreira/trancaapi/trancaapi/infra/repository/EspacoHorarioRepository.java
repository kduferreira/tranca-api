package io.github.kduferreira.trancaapi.trancaapi.infra.repository;

import io.github.kduferreira.trancaapi.trancaapi.domain.EspacoHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacoHorarioRepository extends JpaRepository<EspacoHorario, Long> {

}
