package io.github.kduferreira.trancaapi.trancaapi.application;


import io.github.kduferreira.trancaapi.trancaapi.domain.Condominio;
import io.github.kduferreira.trancaapi.trancaapi.domain.Convites;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.ConvitesCondominioDTO;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.ConvitesDTO;
import io.github.kduferreira.trancaapi.trancaapi.domain.dto.ConvitesUsuarioDTO;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.CondominioRepository;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.ConvitesRepository;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.UsuarioRepository;
import io.github.kduferreira.trancaapi.trancaapi.service.ConvitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("convites")
@RequiredArgsConstructor
public class ConvitesController {


    private final ConvitesRepository convitesRepository;


    private final UsuarioRepository usuarioRepository;

    private final CondominioRepository condominioRepository;
    private final ConvitesService convitesService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Convites adicionar (@Valid @RequestBody Convites convites) {
        return convitesService.save(convites);
    }

    @GetMapping
    public List<ConvitesDTO> listar () {
        List<Convites> convites = convitesRepository.findAll();
        return ConvitesDTO.converter(convites);
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<ConvitesUsuarioDTO> mostrarUsuario (@PathVariable Long id) {

        Optional<Usuario> opcional = usuarioRepository.findById(id);
        if (opcional.isPresent()) {
            return ResponseEntity.ok(new ConvitesUsuarioDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/condominio/{id}")
    public ResponseEntity<ConvitesCondominioDTO> mostrarCondominio (@PathVariable Long id) {

        Optional<Condominio> opcional = condominioRepository.findById(id);
        if (opcional.isPresent()) {
            return ResponseEntity.ok(new ConvitesCondominioDTO(opcional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Convites> atualizar (@Valid @PathVariable Long id, @Valid @RequestBody Convites convites){


        if(!convitesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        convites.setId(id);
        convites.setDt_modificacao(OffsetDateTime.now());
        convites = convitesService.save(convites);
        return ResponseEntity.ok(convites);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable Long id) {
        if (!convitesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        convitesService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
