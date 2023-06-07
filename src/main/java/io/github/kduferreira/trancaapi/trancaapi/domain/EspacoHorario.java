package io.github.kduferreira.trancaapi.trancaapi.domain;

import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.DiaDaSemana;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
public class EspacoHorario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = DiaDaSemana.class)
    @CollectionTable(name = "dias_da_semana", joinColumns = @JoinColumn(name = "id_espaco_horario"))
    private Set<DiaDaSemana> diasDaSemana;

    private String horarioInicial;
    private String horarioFinal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public Set<DiaDaSemana> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(Set<DiaDaSemana> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getHorarioInicial() {
        return LocalTime.parse(horarioInicial, DateTimeFormatter.ofPattern("HH:mm"))
                .format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getHorarioFinal() {
        return LocalTime.parse(horarioFinal, DateTimeFormatter.ofPattern("HH:mm"))
                .format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
