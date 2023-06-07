package io.github.kduferreira.trancaapi.trancaapi.service;

import io.github.kduferreira.trancaapi.trancaapi.domain.Espaco;
import io.github.kduferreira.trancaapi.trancaapi.domain.EspacoHorario;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.EspacoHorarioDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.EspacoHorarioRepository;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.EspacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EspacoHorarioService {

    private final EspacoHorarioRepository espacoHorarioRepository;
    private final EspacoRepository espacoRepository;


    @Transactional
    public EspacoHorarioDTO criarEspacoHorario(EspacoHorarioDTO espacoHorarioDTO) {
        EspacoHorario espacoHorario = espacoHorarioDTO.toEntity();

        if (!isEspacoDisponivel(espacoHorario)) {
            throw new RuntimeException("Espaço indisponível no horário solicitado");
        }

        EspacoHorario espacoHorarioSalvo = espacoHorarioRepository.save(espacoHorario);
        return EspacoHorarioDTO.fromEntity(espacoHorarioSalvo);
    }

    public EspacoHorario atualizarEspacoHorario(Long id, EspacoHorarioDTO espacoHorarioDTO) {
        EspacoHorario espacoHorarioExistente = buscarPorId(id);

        espacoHorarioExistente.setDiasDaSemana(espacoHorarioDTO.getDiasDaSemana());
        espacoHorarioExistente.setHorarioInicial(espacoHorarioDTO.getHorarioInicial());
        espacoHorarioExistente.setHorarioFinal(espacoHorarioDTO.getHorarioFinal());

        if (!isEspacoDisponivel(espacoHorarioExistente)) {
            throw new RuntimeException("Espaço indisponível no horário solicitado");
        }

        return espacoHorarioRepository.save(espacoHorarioExistente);
    }


@Transactional
    public void excluirEspacoHorario(Long id) {
        EspacoHorario espacoHorario = buscarPorId(id);
        espacoHorarioRepository.delete(espacoHorario);
    }
@Transactional
    public EspacoHorario buscarPorId(Long id) {
        return espacoHorarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espaço/Horário não encontrado"));
    }
    @Transactional
    public boolean isEspacoDisponivel(EspacoHorario espacoHorario) {
        Espaco espaco = espacoRepository.findById(espacoHorario.getEspaco().getId())
                .orElseThrow(() -> new RuntimeException("Espaço não encontrado"));

        List<EspacoHorario> horariosAgendados = espaco.getEspacoHorarios();

        LocalTime horarioInicial = LocalTime.parse(espacoHorario.getHorarioInicial(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime horarioFinal = LocalTime.parse(espacoHorario.getHorarioFinal(), DateTimeFormatter.ofPattern("HH:mm"));

        for (EspacoHorario horarioAgendado : horariosAgendados) {
            LocalTime horarioAgendadoInicial = LocalTime.parse(horarioAgendado.getHorarioInicial(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime horarioAgendadoFinal = LocalTime.parse(horarioAgendado.getHorarioFinal(), DateTimeFormatter.ofPattern("HH:mm"));

            if (horarioAgendado.getDiasDaSemana().equals(espacoHorario.getDiasDaSemana())
                    && !(horarioAgendadoFinal.isBefore(horarioInicial) || horarioAgendadoInicial.isAfter(horarioFinal))) {
                return false; // Horário já agendado
            }
        }

        return true;
    }

@Transactional
    public List<EspacoHorario> listarTodosEspacosHorario() {
        return espacoHorarioRepository.findAll();
    }
}
