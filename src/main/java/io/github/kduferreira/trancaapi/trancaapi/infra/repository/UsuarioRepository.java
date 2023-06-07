package io.github.kduferreira.trancaapi.trancaapi.infra.repository;


import io.github.kduferreira.trancaapi.trancaapi.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByDocumento(String documento);



        Optional<Usuario> findByemail(String email);
//
//    Optional<Usuario> findbydocumento(String documento);
}
