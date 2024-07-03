package com.doiscs.sisman.views.marca;

import com.doiscs.sisman.domain.model.entity.Marca;
import com.doiscs.sisman.domain.services.MarcaService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBaseGenerics;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.Serial;

@PageTitle("Cadastro Marca")
@Route(value = "marca", layout = MainLayout.class)
public class MarcaForm extends CadastroFormBaseGenerics<Marca, MarcaService> {

    private TextField nome;
    @PropertyId("ativo")
    private Checkbox ckAtivo;

    public MarcaForm() {
        super(Marca.class, "Marca");
    }

    @Override
    protected void initViewComponents() {
        nome = new TextField("Nome");
        ckAtivo = new Checkbox("Ativo ?", true);
        super.formLayout.add(ckAtivo, nome);
    }

    @Override
    protected void updateViewToEdit() {
        btnDelete.setVisible(true);
    }

    @Override
    protected void getNewEntityToPersist() {
        entity = new Marca();
    }
}
