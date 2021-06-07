package me.rayll.proposta.novaproposta.associarnumerocartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import me.rayll.proposta.novaproposta.consultadedados.PropostaAprovacao;

@FeignClient(url = "http://localhost:8888/api/cartoes", name = "criarCartao")
public interface RetornoNumeroDeCartaoFeign {
	@PostMapping
	public String criarCartaoString(@RequestBody PropostaAprovacao propostaAprovacao);
}
