package me.rayll.proposta.novaproposta;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rayll.proposta.cartao.Cartao;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    public boolean existsByDocumento(String documento);
    
    public Set<Proposta> findByEstadoPropostaLikeAndCartaoNotNull(EstadoProposta estadoProposta);

}
