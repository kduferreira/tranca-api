package io.github.kduferreira.trancaapi.trancaapi.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Data
@Entity
public class Motorista {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long usuario_id;


    private String nome;


    private String placa;

    private String carro;


    private LocalTime hora_entrada;

    private LocalTime hora_saida;


}
