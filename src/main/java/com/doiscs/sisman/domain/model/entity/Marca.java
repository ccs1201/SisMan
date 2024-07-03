package com.doiscs.sisman.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
public class Marca extends AbstractEntity {

    @Column(unique = true)
    @NotEmpty(message = "Informe o Nome da marca.")
    @Length(min = 2, max = 25, message = "Descrição da marca deve conter entre 2 e 25 caracteres.")
    private String nome;
    @OneToMany(mappedBy = "marca")
    private List<ModeloEquipamento> modeloEquipamentos;
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ModeloEquipamento> getModeloEquipamentos() {
        return modeloEquipamentos;
    }

    public void setModeloEquipamentos(List<ModeloEquipamento> modeloEquipamentos) {
        this.modeloEquipamentos = modeloEquipamentos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
