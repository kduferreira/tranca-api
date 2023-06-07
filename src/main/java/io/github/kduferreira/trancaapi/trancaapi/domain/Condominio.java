package io.github.kduferreira.trancaapi.trancaapi.domain;



import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Condominio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotBlank
    @Size(max = 14)
    private String cnpj;
    @NotBlank
    @Size(max = 150)
    private String descricao;

    @NotBlank
    @Size(max = 255)
    private String endereco;
    @NotBlank
    @Size(max = 11)
    private String telefone;
    @NotBlank
    @Size(max = 6)
    private String numeroEndereco;
    private String endereco_opcional;
    @NotBlank
    @Size(max = 15)
    private String latitude;
    @NotBlank
    @Size(max = 15)
    private String longetude;

    @OneToMany
    @JoinColumn(name = "condominio_id")
    private List<Usuario> usuarios;

    @OneToMany
    @JoinColumn(name = "condominio_id")
    private List<Convites> convites;

    @OneToMany
    @JoinColumn(name = "condominio_id")
    private List<Espaco> espacos;

}
