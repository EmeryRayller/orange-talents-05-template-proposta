package me.rayll.proposta.novaproposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class NovaProposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    public NovaProposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public NovaPropostaDTO toDTO() {
        return new NovaPropostaDTO(
                this.documento,
                this.email,
                this.nome,
                this.endereco,
                this.salario
        );
    }
}
