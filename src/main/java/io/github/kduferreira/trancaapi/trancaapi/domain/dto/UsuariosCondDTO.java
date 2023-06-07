package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuariosCondDTO {


    private Long id;
    private String nome;

    private List<Usuario> usuarios;


    public UsuariosCondDTO(Condominio condominio) {

        this.id = condominio.getId();
        this.nome = condominio.getNome();
        this.usuarios = condominio.getUsuarios();
    }


    public static List<UsuariosCondDTO> converter(List<Condominio> condominios) {

        return condominios.stream().map(UsuariosCondDTO::new).collect(Collectors.toList());
    }
}
