package io.github.kduferreira.trancaapi.trancaapi.application;


import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.CondominioCnpjDTO;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.ListCondominioDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.CondominioRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.CondominioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("condominio")
@RequiredArgsConstructor
public class CondominioController {

    private final CondominioRepository condominioRepository;
    private final CondominioService condominioService;


    @GetMapping
    public List<ListCondominioDTO> listar() {
        List<Condominio> condominios = condominioRepository.findAll();
        return ListCondominioDTO.converter(condominios);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Condominio adicionar (@Valid @RequestBody Condominio condominio) {
        return condominioService.salvar(condominio);
    }


    @GetMapping("/{cnpj}")
    public ResponseEntity<CondominioCnpjDTO> detalharcnpj (@PathVariable String cnpj) {
        Optional<Condominio> opcional = condominioRepository.findByCnpj(cnpj);
        if(opcional.isPresent()) {
            return ResponseEntity.ok(new CondominioCnpjDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //listar os condominios atraves do ID

    @GetMapping("/id/{id}")
    public ResponseEntity<CondominioCnpjDTO> detalharcnpj (@PathVariable Long id) {
        Optional<Condominio> opcional = condominioRepository.findById(id);
        if(opcional.isPresent()) {
            return ResponseEntity.ok(new CondominioCnpjDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Condominio> atualizar(@Valid @PathVariable  Long id, @Valid @RequestBody Condominio condominio) {

        if(!condominioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        condominio.setId(id);
        condominio  = condominioService.salvar(condominio);
        return ResponseEntity.ok(condominio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable Long id) {
        if(!condominioRepository.existsById(id)) {
            return  ResponseEntity.notFound().build();
        }
        condominioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
