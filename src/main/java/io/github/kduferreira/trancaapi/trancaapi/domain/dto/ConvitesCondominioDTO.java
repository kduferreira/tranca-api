package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.domain.Convites;
import lombok.Data;

import java.util.List;

@Data
public class ConvitesCondominioDTO {

    private Long condominio_id;

    private List<Convites> convites;


    public ConvitesCondominioDTO(Condominio condominio) {


        this.condominio_id = condominio.getId();
        this.convites = condominio.getConvites();

    }

}
