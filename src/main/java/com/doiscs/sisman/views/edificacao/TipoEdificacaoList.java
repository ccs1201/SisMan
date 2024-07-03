package com.doiscs.sisman.views.edificacao;

import com.doiscs.sisman.domain.model.entity.TipoEdificacao;
import com.doiscs.sisman.domain.services.TipoEdificacaoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem Tipo Edficação")
@Route(value = "tipoedificacaolist", layout = MainLayout.class)
public class TipoEdificacaoList extends ListFormBase<TipoEdificacao, TipoEdificacaoService> {

    public TipoEdificacaoList() {
    }

    @Override
    protected void initViewComponents() {
        grid = new Grid<TipoEdificacao>(TipoEdificacao.class, false);
        grid.addColumns("nome", "descricao");

        grid.addColumn(new ComponentRenderer<>(var -> {
            Checkbox ck = new Checkbox();
            ck.setValue(var.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");
    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(TipoEdificacaoForm.class);
    }
}
