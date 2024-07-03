package com.doiscs.sisman.views.ambiente;

import com.doiscs.sisman.domain.model.entity.Ambiente;
import com.doiscs.sisman.domain.services.AmbienteService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem de Ambiente")
@Route(value = "ambientelist", layout = MainLayout.class)
public class AmbienteList extends ListFormBase<Ambiente, AmbienteService> {

    private static final long serialVersionUID = 2748596595018861685L;

    @Override
    protected void initViewComponents() {

        grid = new Grid<Ambiente>(Ambiente.class, false);

        grid.addColumns("nome", "unidadeEdificacao.nome", "unidadeEdificacao.edificacao.nome", "descricao");

        grid.getColumnByKey("unidadeEdificacao.nome").setHeader("Unidade Edificação");
        grid.getColumnByKey("unidadeEdificacao.edificacao.nome").setHeader("Nome Edificação");
        grid.getColumnByKey("descricao").setHeader("Descrição");
        grid.getColumnByKey("nome").setHeader("Nome Ambiente");

        grid.addColumn(new ComponentRenderer<>(e -> {
            Checkbox ck = new Checkbox();
            ck.setValue(e.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");

    }

    @Override
    protected void updateGrid() {
        if (txtpesquisa == null || txtpesquisa.isEmpty()) { //
            grid.setItems(service.findAllEager());
        } else {
            grid.setItems(service.findByNomeEager(txtpesquisa.getValue())); //
        }
    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(AmbienteForm.class);
    }
}
