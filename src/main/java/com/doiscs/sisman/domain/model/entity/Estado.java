package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Estado extends AbstractEntity {

    private String nome;
    private String sigla;
    @OneToMany(mappedBy = "estado")
    private List<Municipio> municipios;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }


}
