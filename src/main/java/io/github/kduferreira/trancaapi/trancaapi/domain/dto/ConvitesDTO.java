package io.github.kduferreira.trancaapi.trancaapi.domain.dto;


import io.github.kduferreira.trancaapi.trancaapi.domain.Convites;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.StatusConvite;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.TipoConvidado;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ConvitesDTO {


    private Long usuario_id;
    private Long condominio_id;
    private String nome;
    private String documento;
    private TipoConvidado tipo;
    private LocalDate dt_entrada;
    private LocalDate dt_saida;
    private LocalTime hr_inicio;
    private LocalTime hr_fim;

    private StatusConvite status;
    public ConvitesDTO(Convites convites) {

        this.usuario_id = convites.getUsuario_id();
        this.condominio_id = convites.getCondominio_id();
        this.nome = convites.getNome();
        this.documento = convites.getDocumento();
        this.tipo = convites.getTipo();
        this.dt_entrada = convites.getDt_entrada();
        this.dt_saida = convites.getDt_saida();
        this.hr_inicio = convites.getHr_inicio();
        this.hr_fim = convites.getHr_fim();
        this.status = convites.getStatus();
    }

    public static List<ConvitesDTO> converter(List<Convites> convites) {

        return convites.stream().map(ConvitesDTO::new).collect(Collectors.toList());
    }
}
