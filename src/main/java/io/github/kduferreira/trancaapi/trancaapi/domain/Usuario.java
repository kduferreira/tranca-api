package io.github.kduferreira.trancaapi.trancaapi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.TipoUsuario;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull
    private Long condominio_id;

    @Column
    @NotBlank
    private String nome;
    @Column
    @NotBlank
    private String sobrenome;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoUsuario usuario;
    @Column
    @NotBlank
    private String documento;
    @Column

    private String email;
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String senha;
    @Column
    @NotBlank
    private String cidade;
    @Column
    @NotBlank
    private String unidade;
    @Column
    private OffsetDateTime Dt_criacao;

    @Column
    private OffsetDateTime Dt_atualizacao;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_perfis",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfis = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Convites> convites;

    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<Contatos> contatos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCondominio_id() {
        return condominio_id;
    }

    public void setCondominio_id(Long condominio_id) {
        this.condominio_id = condominio_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public TipoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TipoUsuario usuario) {
        this.usuario = usuario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public OffsetDateTime getDt_criacao() {
        return Dt_criacao;
    }

    public void setDt_criacao(OffsetDateTime dt_criacao) {
        Dt_criacao = dt_criacao;
    }

    public OffsetDateTime getDt_atualizacao() {
        return Dt_atualizacao;
    }

    public void setDt_atualizacao(OffsetDateTime dt_atualizacao) {
        Dt_atualizacao = dt_atualizacao;
    }

    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }




    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNome()))
                .collect(Collectors.toList());


    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.senha;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Convites> getConvites() {
        return convites;
    }

    public void setConvites(List<Convites> convites) {
        this.convites = convites;
    }

    public List<Contatos> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contatos> contatos) {
        this.contatos = contatos;
    }
}
