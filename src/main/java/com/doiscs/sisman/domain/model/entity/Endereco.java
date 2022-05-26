package com.doiscs.sisman.domain.model.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {

    @NotEmpty(message = "Informe o CEP")
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;

    @NotNull(message = "Informe o Estado.")
    @OneToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @NotNull(message = "Informe o Município.")
    @OneToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

}
