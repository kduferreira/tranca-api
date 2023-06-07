package io.github.kduferreira.trancaapi.trancaapi.domain;

import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.DiaDaSemana;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Entity
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


@ManyToOne
@JoinColumn(name = "espacoHorario_id")
private EspacoHorario espacoHorario;


    private DiaDaSemana diaDaSemana;

@Transient
    private String horarioString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EspacoHorario getEspacoHorario() {
        return espacoHorario;
    }

    public void setEspacoHorario(EspacoHorario espacoHorario) {
        this.espacoHorario = espacoHorario;
    }

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getHorarioString() {
        return horarioString;
    }

    public void setHorarioString(String horarioString) {
        this.horarioString = horarioString;
    }

    public LocalTime getHorario() {
        return LocalTime.parse(horarioString, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setHorario(LocalTime horario) {
        this.horarioString = horario.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
