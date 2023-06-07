package io.github.kduferreira.trancaapi.trancaapi.service;

import io.github.kduferreira.trancaapi.trancaapi.domain.Enum.TipoUsuario;
import io.github.kduferreira.trancaapi.trancaapi.domain.Perfil;
import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import io.github.kduferreira.trancaapi.trancaapi.exception.NegocioException;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.PerfilRepository;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CatalogoUsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final PerfilRepository perfilRepository;

    public Usuario buscar (Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Usuario não existe."));

    }
    @Transactional
    public Usuario salvar (Usuario usuario) {
        boolean documentoEmUso = usuarioRepository.findByDocumento(usuario.getDocumento())
                .stream()
                .anyMatch(UsuarioExistente -> !UsuarioExistente.equals(usuario));



        if(documentoEmUso) {
            throw new NegocioException("Ja existe um usuario cadastrado com esse documento");
        }

        boolean emailEmUso = usuarioRepository.findByemail(usuario.getEmail())
                .stream()
                .anyMatch(UsuarioExistente -> !UsuarioExistente.equals(usuario));



        if(emailEmUso) {
            throw new NegocioException("Ja existe um usuario cadastrado com esse email");
        }

        List<Perfil> perfis = new ArrayList<>();

        // Verificar o perfil especificado no momento da criação do usuário
        if (usuario.getUsuario() == TipoUsuario.MORADOR) {
            // Buscar o perfil de morador
            Perfil morador = perfilRepository.findByNome("MORADOR")
                    .orElseThrow(() -> new IllegalStateException("Perfil de morador não encontrado."));
            validateUniqueProfile(Collections.singletonList(morador), "MORADOR");
            perfis.add(morador);
        } else if (usuario.getUsuario() == TipoUsuario.ADMINISTRADOR) {
            // Buscar o perfil de administrador
            List<Perfil> administradorList = perfilRepository.findByNome("ADMINISTRADOR")
                    .map(Collections::singletonList)
                    .orElseThrow(() -> new IllegalStateException("Perfil de administrador não encontrado."));
            validateUniqueProfile(administradorList, "ADMINISTRADOR");
            perfis.addAll(administradorList);
        } else if (usuario.getUsuario() == TipoUsuario.PORTEIRO) {
            // Buscar o perfil de porteiro
            List<Perfil> porteiroList = perfilRepository.findByNome("PORTEIRO")
                    .map(Collections::singletonList)
                    .orElseThrow(() -> new IllegalStateException("Perfil de porteiro não encontrado."));
            validateUniqueProfile(porteiroList, "PORTEIRO");
            perfis.addAll(porteiroList);
        } else if (usuario.getUsuario() == TipoUsuario.ADMINISTRADOR_DO_CONDOMINIO) {
            // Buscar o perfil de administrador de condomínio
            List<Perfil> adminCondominioList = perfilRepository.findByNome("ADMINISTRADOR DO CONDOMINIO")
                    .map(Collections::singletonList)
                    .orElseThrow(() -> new IllegalStateException("Perfil de administrador de condomínio não encontrado."));
            validateUniqueProfile(adminCondominioList, "ADMINISTRADOR DO CONDOMINIO");
            perfis.addAll(adminCondominioList);
        }
        usuario.setPerfis(perfis);
        usuario.setDt_criacao(OffsetDateTime.now());

        return usuarioRepository.save(usuario);
    }


    private void validateUniqueProfile(List<Perfil> perfis, String nomePerfil) {
        long count = perfis.stream()
                .filter(perfil -> perfil.getNome().equalsIgnoreCase(nomePerfil))
                .count();

        if (count > 1) {
            throw new IllegalStateException("Mais de um perfil encontrado para o nome: " + nomePerfil);
        }
    }

    @Transactional
    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }
    }