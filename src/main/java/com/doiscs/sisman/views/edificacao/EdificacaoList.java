package com.doiscs.sisman.views.edificacao;

import com.doiscs.sisman.domain.model.entity.Edificacao;
import com.doiscs.sisman.domain.services.impl.EdificacaoService;
import com.doiscs.sisman.exceptions.DoisCsViewExcepion;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;

@PageTitle("Listagem de Edificações")
@Route(value = "edificacaolist", layout = MainLayout.class)
public class EdificacaoList extends ListFormBase<Edificacao, EdificacaoService> {

    @Override
    protected void initViewComponents() {
        initGrid();
    }

    private void initGrid() {
        grid = new Grid<Edificacao>(Edificacao.class, false);
        grid.addColumns("nome", "localizacao", "descricao", "tipoEdificacao.nome");

        grid.getColumnByKey("localizacao").setHeader("Localização");
        grid.getColumnByKey("descricao").setHeader("Descrição");
        grid.getColumnByKey("tipoEdificacao.nome").setHeader("Tipo Edificação");

        grid.addColumn(new ComponentRenderer<>(var -> {
            Checkbox ck = new Checkbox();
            ck.setValue(var.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");
    }

    @Override
    protected void edit() {
        try {
            Edificacao edificacao = getEntityFromGrid();
            edificacao.setUnidades(service.getUnidades(edificacao));
            VaadinServletRequest.getCurrent().setAttribute("entityToEdit", edificacao);
            navigateTo();
        } catch (DoisCsViewExcepion e) {
            showWarnig(e.getLocalizedMessage());
        }
    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(EdificacaoForm.class);
    }
}
