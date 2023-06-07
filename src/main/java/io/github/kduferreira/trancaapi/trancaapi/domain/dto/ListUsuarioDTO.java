package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.TipoUsuario;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ListUsuarioDTO {



    private String nome;
    private String sobrenome;
    private LocalDate data_nascimento;
    @Enumerated(EnumType.STRING)
    private TipoUsuario usuario;
    private String documento;
    private String email;
    private String senha;
    private String cidade;
    private String unidade;
    private OffsetDateTime dt_criacao;

    public ListUsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.data_nascimento = usuario.getData_nascimento();
        this.usuario = usuario.getUsuario();
        this.documento = usuario.getDocumento();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.cidade = usuario.getCidade();
        this.unidade = usuario.getUnidade();
        this.dt_criacao = usuario.getDt_criacao();
    }

    public static List<ListUsuarioDTO> converter(List<Usuario> usuarios) {

        return usuarios.stream().map(ListUsuarioDTO::new).collect(Collectors.toList());
    }
}
