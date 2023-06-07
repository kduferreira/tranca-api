package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Espaco;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.RespeitaCapacidade;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EspacoDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private Integer capacidade;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private RespeitaCapacidade respeitaCapacidade;

    @NotBlank
    private String descricao;


    public EspacoDTO(Espaco espaco) {

        this.nome = espaco.getNome();
        this.capacidade = espaco.getCapacidade();
        this.respeitaCapacidade = espaco.getRespeitaCapacidade();
        this.descricao = espaco.getDescricao();

    }

    public static List<EspacoDTO> converter (List<Espaco> espacos) {

        return espacos.stream().map(EspacoDTO:: new).collect(Collectors.toList());
    }
}
