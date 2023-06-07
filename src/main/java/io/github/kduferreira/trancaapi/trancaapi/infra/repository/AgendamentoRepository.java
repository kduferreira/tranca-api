package io.github.kduferreira.trancaapi.trancaapi.infra.repository;

import io.github.kduferreira.trancaapi.trancaapi.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


}
