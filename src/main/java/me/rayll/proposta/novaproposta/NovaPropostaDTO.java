package me.rayll.proposta.novaproposta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaDTO {

    @JsonIgnore
    private Long id;
    @CPF
    private String documento;
    @NotEmpty @Email
    private String email;
    @NotEmpty
    private String nome;
    @NotNull @Positive
    private BigDecimal salario;
    @NotNull
    private EnderecoProposta endereco;

    @Deprecated
    private NovaPropostaDTO() {
    }

    public NovaPropostaDTO(String documento, String email, String nome, BigDecimal salario, EnderecoProposta endereco) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
    }

    public NovaProposta toModel() {
        return new NovaProposta(
                this.documento,
                this.email,
                this.nome,
                this.salario,
                this.endereco);
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EnderecoProposta getEndereco() {
        return endereco;
    }
}
