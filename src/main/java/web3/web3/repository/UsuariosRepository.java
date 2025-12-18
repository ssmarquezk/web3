package web3.web3.repository;

import java.util.Optional;

import web3.web3.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
    Optional<UsuariosModel> findByEmail(String email);
}