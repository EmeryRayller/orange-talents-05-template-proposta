package me.rayll.proposta.novaproposta;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    public boolean existsByDocumento(String documento);
    
    public Set<Proposta> findByEstadoPropostaLikeAndCartaoNull(EstadoProposta estadoProposta);

}
