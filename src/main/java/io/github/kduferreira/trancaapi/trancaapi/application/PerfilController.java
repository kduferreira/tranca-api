package io.github.kduferreira.trancaapi.trancaapi.application;

import io.github.kduferreira.trancaapi.trancaapi.domain.Perfil;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfis")

public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> listarPerfis() {
        return perfilRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable Long id) {
        Optional<Perfil> optionalPerfil = perfilRepository.findById(id);
        if (optionalPerfil.isPresent()) {
            return ResponseEntity.ok(optionalPerfil.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Perfil> criarPerfil(@RequestBody Perfil perfil) {
        Perfil perfilSalvo = perfilRepository.save(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfilAtualizado) {
        Optional<Perfil> optionalPerfil = perfilRepository.findById(id);
        if (optionalPerfil.isPresent()) {
            Perfil perfil = optionalPerfil.get();
            perfil.setNome(perfilAtualizado.getNome());
            Perfil perfilSalvo = perfilRepository.save(perfil);
            return ResponseEntity.ok(perfilSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPerfil(@PathVariable Long id) {
        Optional<Perfil> optionalPerfil = perfilRepository.findById(id);
        if (optionalPerfil.isPresent()) {
            perfilRepository.delete(optionalPerfil.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}