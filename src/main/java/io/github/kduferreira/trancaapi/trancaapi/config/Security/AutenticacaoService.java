package io.github.kduferreira.trancaapi.trancaapi.config.Security;


import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService{

// service para verificar se o usuario existe no banco de dados atraves do email para poder fazer a autenticação do token em seguida

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByemail(username);
        if(usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados invalidos");
    }

}
