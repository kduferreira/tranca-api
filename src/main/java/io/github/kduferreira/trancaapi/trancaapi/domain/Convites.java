package io.github.kduferreira.trancaapi.trancaapi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.StatusConvite;
import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.TipoConvidado;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Data
public class Convites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull
    private Long usuario_id;

    @Valid
    @NotNull
    private Long condominio_id;

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoConvidado tipo;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dt_entrada;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dt_saida;
    @NotNull
    private LocalTime hr_inicio;

    @NotNull
    private LocalTime hr_fim;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusConvite status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dt_criacao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dt_modificacao;
}
