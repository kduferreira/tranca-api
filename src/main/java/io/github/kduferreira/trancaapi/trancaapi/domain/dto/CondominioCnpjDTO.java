package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import lombok.Data;

@Data
public class CondominioCnpjDTO {

    private Long id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String telefone;
    private String descricao;
    private String latitude;
    private String longetude;

    public CondominioCnpjDTO(Condominio condominio) {

        this.id = condominio.getId();
        this.cnpj = condominio.getCnpj();
        this.nome = condominio.getNome();
        this.endereco = condominio.getEndereco();
        this.telefone = condominio.getTelefone();
        this.descricao = condominio.getDescricao();
        this.latitude = condominio.getLatitude();
        this.longetude = condominio.getLongetude();

    }
}
