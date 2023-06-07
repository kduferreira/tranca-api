package io.github.kduferreira.trancaapi.trancaapi.domain.dto;

import io.github.kduferreira.trancaapi.trancaapi.domain.Contatos;
import io.github.kduferreira.trancaapi.trancaapi.domain.Motorista;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class MotoristaDTO {



    private Long usuario_id;


    private String nome;


    private String placa;

    private String carro;


    private LocalTime hora_entrada;

    private LocalTime hora_saida;



    public MotoristaDTO(Motorista motorista) {
        this.usuario_id = motorista.getUsuario_id();
        this.nome = motorista.getNome();
        this.placa = motorista.getPlaca();
        this.carro = motorista.getCarro();
        this.hora_entrada = motorista.getHora_entrada();
        this.hora_saida = motorista.getHora_saida();
    }

    public static List<MotoristaDTO> converter(List<Motorista> motoristas) {
        return motoristas.stream().map(MotoristaDTO::new).collect(Collectors.toList());
    }
}
