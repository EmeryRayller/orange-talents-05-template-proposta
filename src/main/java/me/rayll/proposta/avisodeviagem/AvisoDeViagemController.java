package me.rayll.proposta.avisodeviagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import me.rayll.proposta.cartao.Cartao;
import me.rayll.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping("/avisodeviagem")
public class AvisoDeViagemController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private AvistoRepository avisoRepository;
	
	@PostMapping("/{id}")
	public ResponseEntity<Void> salvarAvisoDeViagem(@PathVariable String id, 
			@RequestBody AvisoViagemDTO dto,
			UriComponentsBuilder builder) {
		
		if(id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		Cartao c = cartaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		dto.setIdCartao(id);
		
		AvisoViagem aviso = avisoRepository.save(dto.toModel(c));
		
		return ResponseEntity.ok().build();
	}
}
