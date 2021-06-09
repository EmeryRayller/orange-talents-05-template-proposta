package me.rayll.proposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String>{
	
	public boolean existsById(String id);
}
