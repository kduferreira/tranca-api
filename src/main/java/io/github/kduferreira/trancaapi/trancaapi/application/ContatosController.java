package io.github.kduferreira.trancaapi.trancaapi.application;

import io.github.kduferreira.trancaapi.trancaapi.domain.Contatos;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.ContatosDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.ContatosRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.ContatosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("contatos")
@RequiredArgsConstructor
public class ContatosController {



    private final ContatosRepository contatosRepository;

    private final ContatosService contatosService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contatos adicionar (@Valid @RequestBody Contatos contatos) {
        return contatosService.save(contatos);
    }


    @GetMapping
    public List<ContatosDTO> listar () {
    List<Contatos> contatos = contatosRepository.findAll();
        return ContatosDTO.converter(contatos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contatos> atualizar(@Valid @PathVariable  Long id, @Valid @RequestBody Contatos contatos) {

        if(!contatosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contatos.setId(id);
        contatos  = contatosService.save(contatos);
        return ResponseEntity.ok(contatos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable Long id) {
        if(!contatosRepository.existsById(id)) {
            return  ResponseEntity.notFound().build();
        }
        contatosService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
