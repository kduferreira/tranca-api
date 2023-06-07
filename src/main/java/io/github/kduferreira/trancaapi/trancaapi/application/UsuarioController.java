package io.github.kduferreira.trancaapi.trancaapi.application;


import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;

import io.github.kduferreira.trancaapi.trancaapi.domain.dto.DetalheUsuarioDTO;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.ListUsuarioDTO;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.UsuariosCondDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.CondominioRepository;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.UsuarioRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.CatalogoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final CatalogoUsuarioService catalogoUsuarioService;

    private final CondominioRepository condominioRepository;

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionar (@Valid @RequestBody Usuario usuario) {
        return catalogoUsuarioService.salvar(usuario);
    }
//    @GetMapping
//    public String status () {
//        return  "ok";
   // }
    @GetMapping
    public List<ListUsuarioDTO> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ListUsuarioDTO.converter(usuarios);
    }

    @GetMapping("/{documento}")
    public ResponseEntity<DetalheUsuarioDTO> detalharcpf (@PathVariable String documento) {

        Optional<Usuario> opcional = usuarioRepository.findByDocumento(documento);
        if (opcional.isPresent()) {
            return ResponseEntity.ok(new DetalheUsuarioDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<UsuariosCondDTO> mostrar (@PathVariable String cnpj) {
        Optional<Condominio> opcional = condominioRepository.findByCnpj(cnpj);
        if (opcional.isPresent()) {
            return ResponseEntity.ok(new UsuariosCondDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@Valid @PathVariable  Long id, @Valid @RequestBody Usuario usuario) {

        if(!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuario.setId(id);
        usuario  = catalogoUsuarioService.salvar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        catalogoUsuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
