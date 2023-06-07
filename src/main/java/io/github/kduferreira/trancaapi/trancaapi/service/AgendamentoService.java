package io.github.kduferreira.trancaapi.trancaapi.service;
import java.time.LocalTime;
import io.github.kduferreira.trancaapi.trancaapi.domain.Agendamento;
import io.github.kduferreira.trancaapi.trancaapi.domain.EspacoHorario;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.AgendamentoDTO;
import io.github.kduferreira.trancaapi.trancaapi.exception.AgendamentoException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.AgendamentoRepository;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.EspacoHorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

   private final AgendamentoRepository agendamentoRepository;
   private final EspacoHorarioRepository espacoHorarioRepository;


    public List<AgendamentoDTO> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream()
                .map(AgendamentoDTO::fromEntity)
                .collect(Collectors.toList());
    }
    public AgendamentoDTO criarAgendamento(AgendamentoDTO agendamentoDTO) throws AgendamentoException {
        Agendamento agendamento = agendamentoDTO.toEntity();
        EspacoHorario espacoHorario = espacoHorarioRepository.findById(agendamento.getEspacoHorario().getId())
                .orElseThrow(() -> new AgendamentoException("EspacoHorario não encontrado"));

        if (!espacoHorario.getDiasDaSemana().contains(agendamento.getDiaDaSemana())) {
            throw new AgendamentoException("O espaço não funciona nesse dia da semana");
        }

        if (!verificarHorarioFuncionamento(espacoHorario, agendamento.getHorario())) {
            throw new AgendamentoException("O espaço não funciona nesse horário");
        }

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
        return AgendamentoDTO.fromEntity(agendamentoSalvo);
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    private boolean verificarHorarioFuncionamento(EspacoHorario espacoHorario, LocalTime horario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horarioInicial = LocalTime.parse(espacoHorario.getHorarioInicial(), formatter);
        LocalTime horarioFinal = LocalTime.parse(espacoHorario.getHorarioFinal(), formatter);

        return !horario.isBefore(horarioInicial) && !horario.isAfter(horarioFinal);
    }

    @Transactional
    public Agendamento salvar (Agendamento agendamento) {

        return agendamentoRepository.save(agendamento);
    }
    @Transactional
    public void excluirAgendamento(Long id) {
        Agendamento agendamento = buscarPorId(id);
        agendamentoRepository.delete(agendamento);
    }

}
