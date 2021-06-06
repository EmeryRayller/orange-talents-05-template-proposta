package me.rayll.proposta.novaproposta;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<NovaProposta, Long> {
    public boolean existsByDocumento(String documento);
    
    public Set<NovaProposta> findByEstadoPropostaLikeAndNumeroCartaoLike(EstadoProposta estadoProposta, String numeroCartao);
}
