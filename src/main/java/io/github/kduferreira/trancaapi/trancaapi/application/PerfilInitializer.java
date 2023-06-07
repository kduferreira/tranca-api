package io.github.kduferreira.trancaapi.trancaapi.application;

import io.github.kduferreira.trancaapi.trancaapi.domain.Perfil;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PerfilInitializer implements CommandLineRunner {


    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        inicializarPerfis();
    }

    private void inicializarPerfis() {
        List<Perfil> perfis = Arrays.asList(
                criarPerfil("ADMINISTRADOR")
//                criarPerfil("MORADOR"),
//                criarPerfil("FUNCIONARIO"),
//                criarPerfil("PORTEIRO")
        );

        perfilRepository.saveAll(perfis);
    }

    private Perfil criarPerfil(String nome) {
        Perfil perfil = new Perfil();
        perfil.setNome(nome);
        return perfil;
    }
}
