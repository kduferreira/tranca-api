package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Contatos;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContatosDTO {


    private Long usuario_id;
    private String nome;

    private String documento;


    public ContatosDTO(Contatos contatos) {
        this.usuario_id = contatos.getUsuario_id();
        this.nome = contatos.getNome();
        this.documento = contatos.getDocumento();
    }


    public static List<ContatosDTO> converter(List<Contatos> contatos) {
        return contatos.stream().map(ContatosDTO::new).collect(Collectors.toList());
    }
}
