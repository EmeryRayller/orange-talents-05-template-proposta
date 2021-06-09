package me.rayll.proposta.cadastrobiometria;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Biometria {

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String numeroCartao = "";
	
	private String idBiometria = "";
	
	@Deprecated
	private Biometria() {}
	
	public Biometria(@NotEmpty String numeroCartao, @NotEmpty String idBiometria) {
		this.numeroCartao = numeroCartao;
		this.idBiometria = idBiometria;
	}
	
	public BiometriaDTO toDTO() {
		BiometriaDTO dto = new BiometriaDTO(idBiometria, numeroCartao);
		dto.setId(this.id);
		return dto ;
	}
	
	
}
