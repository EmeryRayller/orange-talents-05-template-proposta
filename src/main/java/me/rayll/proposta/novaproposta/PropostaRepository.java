package me.rayll.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<NovaProposta, Long> {

    Optional<NovaProposta> findByDocumento();
}
