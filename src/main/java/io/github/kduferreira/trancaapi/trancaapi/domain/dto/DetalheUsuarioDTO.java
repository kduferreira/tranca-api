package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.TipoUsuario;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetalheUsuarioDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private Long condominio_id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    private String documento;
    private String email;
    private String cidade;
    private String unidade;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dt_criacao;
    private OffsetDateTime dt_atualizacao;




    public DetalheUsuarioDTO(Usuario usuario) {

        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.condominio_id = usuario.getCondominio_id();
        this.data_nascimento = usuario.getData_nascimento();
        this.tipo = usuario.getUsuario();
        this.documento = usuario.getDocumento();
        this.email = usuario.getEmail();
        this.cidade = usuario.getCidade();
        this.unidade = usuario.getUnidade();
        this.dt_criacao = usuario.getDt_criacao();
        this.dt_atualizacao = usuario.getDt_atualizacao();
    }


    public static List<DetalheUsuarioDTO> converter(List<Usuario> usuarios) {

        return usuarios.stream().map(DetalheUsuarioDTO::new).collect(Collectors.toList());
    }

}
