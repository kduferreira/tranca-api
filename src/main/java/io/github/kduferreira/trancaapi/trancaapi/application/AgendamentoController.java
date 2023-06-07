package io.github.kduferreira.trancaapi.trancaapi.application;

import io.github.kduferreira.trancaapi.trancaapi.domain.Agendamento;
import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.AgendamentoDTO;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.EspacoHorarioDTO;
import io.github.kduferreira.trancaapi.trancaapi.exception.AgendamentoException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.AgendamentoRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {


    private final AgendamentoService agendamentoService;

    private final AgendamentoRepository agendamentoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarAgendamento(@PathVariable Long id) throws Exception {
        Agendamento agendamento = agendamentoService.buscarPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AgendamentoDTO> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) throws AgendamentoException {
        AgendamentoDTO responseDTO = agendamentoService.criarAgendamento(agendamentoDTO);
        return ResponseEntity.ok(responseDTO);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@Valid @PathVariable  Long id, @Valid @RequestBody Agendamento agendamento) {

        if(!agendamentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        agendamento.setId(id);
        agendamento  = agendamentoService.salvar(agendamento);
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirAgendamento(@PathVariable Long id) {
        agendamentoService.excluirAgendamento(id);
    }

}
