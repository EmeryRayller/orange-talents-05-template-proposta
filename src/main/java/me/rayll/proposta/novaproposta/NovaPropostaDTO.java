package me.rayll.proposta.novaproposta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.rayll.proposta.validacoes.annotations.CPFouCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaDTO {

    @JsonIgnore
    private Long id;
    @NotEmpty @CPFouCNPJ
    private String documento;
    @NotEmpty @Email
    private String email;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;

    @Deprecated
    private NovaPropostaDTO() {
    }

    public NovaPropostaDTO(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public NovaProposta toModel() {
        return new NovaProposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }


    public String getDocumento() {
        return this.documento;
    }

    public Long getId() {
        return this.id;
    }
}
