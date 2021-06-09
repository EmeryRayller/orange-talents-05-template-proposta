package me.rayll.proposta.cadastrobiometria;

import java.net.URI;
import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import me.rayll.proposta.cartao.CartaoRepository;
import me.rayll.proposta.novaproposta.PropostaRepository;

@RestController
@RequestMapping("/cadastrobiometria")
@Transactional
public class BiometriaController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BiometricaRepository biometriaRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastroBiometrica(@RequestBody BiometriaDTO dto, UriComponentsBuilder uri){
		
		if(dto.getIdBiometria().isEmpty()) {
			throw new IllegalArgumentException("A biometria não foi coletada corretamente!");
		}
		
		if(!cartaoRepository.existsById(dto.getNumeroCartao())) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Cartão não existe!");
		}
		
		String bioEncoder = Base64.getEncoder().encodeToString(dto.getIdBiometria().getBytes());
		dto.setIdBiometria(bioEncoder);
		
		Biometria biometria = biometriaRepository.save(dto.toModel());
		
		URI uri2 = uri.path("/cadastrobiometria/{id}").buildAndExpand( biometria.toDTO().getId()).toUri();
		
		return ResponseEntity.status(HttpStatus.CREATED).header("location", uri2.toString()).build();
	}
}