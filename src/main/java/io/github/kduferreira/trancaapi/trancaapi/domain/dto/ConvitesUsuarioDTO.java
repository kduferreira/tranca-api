package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Convites;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class ConvitesUsuarioDTO {

    private long usuario_id;

    private List<Convites> convites;



    public ConvitesUsuarioDTO(Usuario usuario) {

        this.usuario_id = usuario.getId();
        this.convites = usuario.getConvites();
    }
}
