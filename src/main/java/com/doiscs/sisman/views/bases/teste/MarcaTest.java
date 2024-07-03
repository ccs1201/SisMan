package com.doiscs.sisman.views.bases.teste;

import com.doiscs.sisman.domain.model.entity.Marca;
import com.doiscs.sisman.domain.services.MarcaService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBaseGenerics;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(layout = MainLayout.class, value = "marcatest")
public class MarcaTest extends CadastroFormBaseGenerics<Marca, MarcaService> {
    private TextField nome;

    public MarcaTest() {
        super(Marca.class, "Marca");
    }

    @Override
    protected void getNewEntityToPersist() {
        entity = new Marca();
    }

    @Override
    protected void initViewComponents() {
        nome = new TextField("nome");
        super.formLayout.add(nome);
    }
}
