package com.doiscs.sisman.views.edificacao;

import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;
import com.doiscs.sisman.domain.services.impl.UnidadeEdificacaoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem de Unidades")
@Route(value = "unidadeedificacaolist", layout = MainLayout.class)
public class UnidadeEdificacaoList extends ListFormBase<UnidadeEdificacao, UnidadeEdificacaoService> {

    @Override
    protected void initViewComponents() {
        grid = new Grid<>(UnidadeEdificacao.class, false);
        grid.addColumns("nome", "edificacao.nome");

        grid.getColumnByKey("nome").setHeader("Nome Unidade");
        grid.getColumnByKey("edificacao.nome").setHeader("Edificação");
        grid.addColumn(new ComponentRenderer<>(var -> {
            Checkbox ck = new Checkbox();
            ck.setValue(var.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");
    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(UnidadeEdificacaoForm.class);
    }
}
