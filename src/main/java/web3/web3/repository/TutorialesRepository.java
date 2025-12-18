package web3.web3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web3.web3.model.TutorialesModel;

public interface TutorialesRepository extends JpaRepository<TutorialesModel, Long> {
    List<TutorialesModel> findByUsuarioId(Long usuarioId);
}