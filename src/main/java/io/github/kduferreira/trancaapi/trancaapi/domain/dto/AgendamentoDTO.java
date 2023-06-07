package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.*;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.DiaDaSemana;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


public class AgendamentoDTO {


        private Long id;
        private Long espacoHorarioId;
        private DiaDaSemana diaDaSemana;
        private LocalTime horario;


        public AgendamentoDTO() {

        }
        public AgendamentoDTO(Agendamento agendamento) {
            this.espacoHorarioId = agendamento.getEspacoHorario().getId();
            this.diaDaSemana = agendamento.getDiaDaSemana();
            this.horario = agendamento.getHorario();
            }

            public static AgendamentoDTO fromEntity(Agendamento agendamento) {
            AgendamentoDTO dto = new AgendamentoDTO();
            dto.setId(agendamento.getId());
            dto.setespacoHorarioId(agendamento.getEspacoHorario().getId());
            dto.setDiaDaSemana(agendamento.getDiaDaSemana());
            dto.setHorario(agendamento.getHorario());
            return dto;
            }

            public Agendamento toEntity() {
            Agendamento agendamento = new Agendamento();
            agendamento.setId(this.id);
            EspacoHorario espacoHorario = new EspacoHorario();
            espacoHorario.setId(this.espacoHorarioId);
            agendamento.setEspacoHorario(espacoHorario);
            agendamento.setDiaDaSemana(this.diaDaSemana);
            agendamento.setHorario(this.horario);
            return agendamento;
            }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getespacoHorarioId() {
        return espacoHorarioId;
    }

    public void setespacoHorarioId(Long espacoHorarioId) {
        this.espacoHorarioId = espacoHorarioId;
    }

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHorario() {
        return horario;
    }
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public static List<AgendamentoDTO> converter(List<Agendamento> agendamentos) {

        return agendamentos.stream().map(AgendamentoDTO::new).collect(Collectors.toList());
    }
}

