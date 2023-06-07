package io.github.kduferreira.trancaapi.trancaapi.domain.dto;


import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.DiaDaSemana;
import io.github.kduferreira.trancaapi.trancaapi.domain.Espaco;
import io.github.kduferreira.trancaapi.trancaapi.domain.EspacoHorario;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data

public class EspacoHorarioDTO {

    private Long id;
    private Long espacoId;
    private Set <DiaDaSemana> diasDaSemana;
    private String horarioInicial;
    private String horarioFinal;

    // Construtor vazio
    public EspacoHorarioDTO() {
    }

    // Construtor que aceita um objeto EspacoHorario
    public EspacoHorarioDTO(EspacoHorario espacoHorario) {
        this.espacoId = espacoHorario.getEspaco().getId();
        this.diasDaSemana = espacoHorario.getDiasDaSemana();
        this.horarioInicial = espacoHorario.getHorarioInicial();
        this.horarioFinal = espacoHorario.getHorarioFinal();
    }

    public static EspacoHorarioDTO fromEntity(EspacoHorario espacoHorario) {
        EspacoHorarioDTO dto = new EspacoHorarioDTO();
        dto.setId(espacoHorario.getId());
        dto.setEspacoId(espacoHorario.getEspaco().getId());
        dto.setDiasDaSemana(espacoHorario.getDiasDaSemana());
        dto.setHorarioInicial(espacoHorario.getHorarioInicial());
        dto.setHorarioFinal(espacoHorario.getHorarioFinal());
        return dto;
    }

    public EspacoHorario toEntity() {
        EspacoHorario espacoHorario = new EspacoHorario();
        espacoHorario.setId(this.id);
        Espaco espaco = new Espaco();
        espaco.setId(this.espacoId);
        espacoHorario.setEspaco(espaco);
        espacoHorario.setDiasDaSemana(this.diasDaSemana);
        espacoHorario.setHorarioInicial(this.horarioInicial);
        espacoHorario.setHorarioFinal(this.horarioFinal);
        return espacoHorario;
    }

    public static List<EspacoHorarioDTO> converter(@NotNull List<EspacoHorario> espacoHorarios) {

        return espacoHorarios.stream().map(EspacoHorarioDTO::new).collect(Collectors.toList());

    }

}
