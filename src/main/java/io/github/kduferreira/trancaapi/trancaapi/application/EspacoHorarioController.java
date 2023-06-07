package io.github.kduferreira.trancaapi.trancaapi.application;



import io.github.kduferreira.trancaapi.trancaapi.domain.EspacoHorario;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.EspacoHorarioDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.EspacoHorarioRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.EspacoHorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("EspacoHorario")
@RequiredArgsConstructor
public class EspacoHorarioController {



    private final EspacoHorarioRepository espacoHorarioRepository;

    private final EspacoHorarioService espacoHorarioService;


    @GetMapping
    public List<EspacoHorario> listarTodosEspacosHorario() {
        return espacoHorarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EspacoHorarioDTO> adicionarEspacoHorario(@RequestBody EspacoHorarioDTO espacoHorarioDTO) {
        EspacoHorarioDTO responseDTO = espacoHorarioService.criarEspacoHorario(espacoHorarioDTO);
        return ResponseEntity.ok(responseDTO);
    }




    @PutMapping("/{id}")
    public ResponseEntity<EspacoHorarioDTO> atualizarEspacoHorario(@PathVariable Long id, @RequestBody EspacoHorarioDTO espacoHorarioDTO) {
        try {
            EspacoHorario espacoHorarioAtualizado = espacoHorarioService.atualizarEspacoHorario(id, espacoHorarioDTO);
            EspacoHorarioDTO espacoHorarioDTOAtualizado = espacoHorarioDTO.fromEntity(espacoHorarioAtualizado);
            return ResponseEntity.ok(espacoHorarioDTOAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirEspacoHorario(@PathVariable Long id) {
        espacoHorarioService.excluirEspacoHorario(id);
    }


}
