package io.github.kduferreira.trancaapi.trancaapi.domain;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil  implements GrantedAuthority{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Outros atributos e métodos da classe...

    public Perfil() {
    }

    public Perfil(String nome) {
        this.nome = nome;
    }
    @Override
    public String getAuthority() {
        return nome;
    }
    // Getters e setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}