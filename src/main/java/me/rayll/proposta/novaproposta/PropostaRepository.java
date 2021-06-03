package me.rayll.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<NovaProposta, Long> {
    public boolean existsByDocumento(String documento);
}
