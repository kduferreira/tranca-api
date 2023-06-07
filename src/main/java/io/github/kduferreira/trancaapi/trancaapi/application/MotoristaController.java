package io.github.kduferreira.trancaapi.trancaapi.application;

import io.github.kduferreira.trancaapi.trancaapi.domain.Motorista;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.MotoristaDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.MotoristaRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.MotoristaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("motorista")
@RequiredArgsConstructor
public class MotoristaController {

    private final MotoristaRepository motoristaRepository;

    private final MotoristaService motoristaService;



@PostMapping
@ResponseStatus(HttpStatus.CREATED)
    public Motorista adicionar (@Valid @RequestBody Motorista motorista) {
        return motoristaService.save(motorista);
    }


    @GetMapping
    public List<MotoristaDTO> listar () {
    List<Motorista> motoristas = motoristaRepository.findAll();
    return MotoristaDTO.converter(motoristas);
    }


    @GetMapping("/motorista/{placa}")
    public ResponseEntity<MotoristaDTO> mostrarplaca (@PathVariable String placa ) {

        Optional<Motorista> optional = motoristaRepository.findByPlaca(placa);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new MotoristaDTO(optional.get()));
        }
        return  ResponseEntity.notFound().build();
    }

    public ResponseEntity<Motorista> atualizar (@Valid @PathVariable Long id, @Valid @RequestBody Motorista motorista){


    if(!motoristaRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }
    motorista.setId(id);
    motorista = motoristaService.save(motorista);
    return  ResponseEntity.ok(motorista);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable Long id) {
        if (!motoristaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        motoristaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
