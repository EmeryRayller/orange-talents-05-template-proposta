package me.rayll.proposta.novaproposta.associarnumerocartao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.rayll.proposta.novaproposta.EstadoProposta;
import me.rayll.proposta.novaproposta.NovaProposta;
import me.rayll.proposta.novaproposta.NovaPropostaDTO;
import me.rayll.proposta.novaproposta.PropostaRepository;
import me.rayll.proposta.novaproposta.consultadedados.PropostaAprovacao;

@EnableScheduling
@Component
public class AcompanhamentoNumeroCartaoSchedule {

	private final String EMPTY_STRING = "";
	
	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private RetornoNumeroDeCartaoFeign retornoCartaoFeign;

	@Async
	@Scheduled(fixedDelay = 5000L, initialDelay = 10000L)
	public ResponseEntity<String> executaBuscaDePropostasSemNumeroDeCartao() throws JsonMappingException, JsonProcessingException {
		
		Set<NovaProposta> listaBuscada = 
				propostaRepository.findByEstadoPropostaLikeAndNumeroCartaoLike(EstadoProposta.ELEGIVEL, EMPTY_STRING);
		
		if(listaBuscada.size() > 0) {
			try {
				for (NovaProposta novaProposta : listaBuscada) {
					String s = retornoCartaoFeign.criarCartaoString(new PropostaAprovacao(
							novaProposta.toDTO().getDocumento(),
							novaProposta.toDTO().getNome(), 
							novaProposta.toDTO().getId()));
					
					ObjectMapper mapper = new ObjectMapper();
					
					String id = mapper.readValue(s, JsonNode.class).get("id").asText();
					
					novaProposta.setNumeroCartao(id);
					propostaRepository.save(novaProposta);
				}
			} catch (ResponseStatusException e) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
			}
		}
		return ResponseEntity.ok("");
	}
}
