package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ListCondominioDTO {

    private String nome;
    private String cpnj;
    private String descricao;
    private String endereco;
    private String numeroEndereco;
    private String endereco_opcional;
    private String latidude;
    private String longetude;

    public ListCondominioDTO(Condominio condominio) {
        this.nome = condominio.getNome();
        this.cpnj = condominio.getCnpj();
        this.descricao = condominio.getDescricao();
        this.endereco = condominio.getEndereco();
        this.numeroEndereco = condominio.getNumeroEndereco();
        this.endereco_opcional = condominio.getEndereco_opcional();
        this.latidude = condominio.getLatitude();
        this.longetude = condominio.getLongetude();
    }

    public static List<ListCondominioDTO> converter(List<Condominio> condominios) {
        return condominios.stream().map(ListCondominioDTO::new).collect(Collectors.toList());
    }

}
