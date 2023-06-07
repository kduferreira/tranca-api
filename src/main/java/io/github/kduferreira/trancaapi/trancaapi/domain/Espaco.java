package io.github.kduferreira.trancaapi.trancaapi.domain;


import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.RespeitaCapacidade;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Valid
    @NotNull
    private Long condominio_id;

    @NotBlank
    @Column
    private String nome;

    @NotNull
    @Column
    private Integer capacidade;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private RespeitaCapacidade respeitaCapacidade;

    @Column
    @NotBlank
    private String descricao;

//    @OneToMany
//    @JoinColumn(name = "espaco_id")
//    private List<ReservarEspaco> reservarEspacos;
//
   @OneToMany
   @JoinColumn(name = "espaco_id")
    private List<EspacoHorario> espacoHorarios;
}
