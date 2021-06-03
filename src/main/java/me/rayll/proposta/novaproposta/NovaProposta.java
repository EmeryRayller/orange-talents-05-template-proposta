package me.rayll.proposta.novaproposta;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Deprecated
    private NovaProposta(){}

    public NovaProposta(String documento, String email, String nome, BigDecimal salario, EnderecoProposta endereco) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
    }

    public NovaPropostaDTO toDTO() {
        return new NovaPropostaDTO(
                this.documento,
                this.email,
                this.nome,
                this.salario,
                this.endereco
        );
    }
}
