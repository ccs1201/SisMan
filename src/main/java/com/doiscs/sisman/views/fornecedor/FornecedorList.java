package com.doiscs.sisman.views.fornecedor;

import com.doiscs.sisman.domain.model.entity.Fornecedor;
import com.doiscs.sisman.domain.services.impl.FornecedorService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem de Fornecedor")
@Route(value = "fornecedorlist", layout = MainLayout.class)
public class FornecedorList extends ListFormBase<Fornecedor, FornecedorService> {

    @Override
    protected void initViewComponents() {

        grid = new Grid<>(Fornecedor.class, false);

        grid.addColumns("nomeFantasia", "razaoSocial", "cnpj", "telefone", "endereco.estado.nome",
                "endereco.municipio.nome");

        grid.getColumnByKey("nomeFantasia").setHeader("Nome Fantasia");
        grid.getColumnByKey("razaoSocial").setHeader("Razão Social");
        grid.getColumnByKey("endereco.estado.nome").setHeader("Estado");
        grid.getColumnByKey("endereco.municipio.nome").setHeader("Muncicípio");

        grid.addColumn(new ComponentRenderer<>(fornecedor -> {
            Checkbox ck = new Checkbox();
            ck.setValue(fornecedor.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");

    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(FornecedorForm.class);

    }

}
