package io.github.kduferreira.trancaapi.trancaapi.application;


import io.github.kduferreira.trancaapi.trancaapi.domain.Espaco;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.EspacoDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.EspacoRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.EspacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("espaco")
@RequiredArgsConstructor
public class EspacoController {


    private final EspacoRepository espacoRepository;

    private final EspacoService espacoService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Espaco cadastrar (@RequestBody Espaco espaco) {
        return espacoService.save(espaco);
    }

    @GetMapping
    public List<EspacoDTO> listar () {
        List<Espaco> espacos = espacoRepository.findAll();
        return EspacoDTO.converter(espacos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacoDTO> detalhar (@PathVariable Long id) {
        Optional<Espaco> opcional = espacoRepository.findById(id);
        if(opcional.isPresent()) {
            return ResponseEntity.ok(new EspacoDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Espaco> atualizar (@Valid @PathVariable Long id, @Valid @RequestBody Espaco espaco) {

        if(!espacoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        espaco.setId(id);
        espaco = espacoService.save(espaco);
        return ResponseEntity.ok(espaco);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id ) {

        if(!espacoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        espacoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
