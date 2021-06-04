package me.rayll.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import me.rayll.proposta.novaproposta.consultadedados.PropostaAprovacao;

@Entity
public class NovaProposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    @Embedded
    private EnderecoProposta endereco;
    private BigDecimal salario;
    private EstadoProposta estadoProposta;

    @Deprecated
    private NovaProposta(){}

    public NovaProposta(String documento, String email, String nome, BigDecimal salario, EnderecoProposta endereco, EstadoProposta proposta) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
        this.estadoProposta = proposta;
    }

    public NovaPropostaDTO toDTO() {
        NovaPropostaDTO propostaDTO = new NovaPropostaDTO(
                this.documento,
                this.email,
                this.nome,
                this.salario,
                this.endereco
        );
        propostaDTO.setEstadoProposta(this.estadoProposta);
        propostaDTO.setId(this.id);
        return propostaDTO;
    }
	
}
