package com.doiscs.sisman.views.equipamento;

import com.doiscs.sisman.domain.model.entity.ModeloEquipamento;
import com.doiscs.sisman.domain.services.impl.ModeloEquipamentoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem Modelo Equipamentos")
@Route(value = "modeloequipamentolist", layout = MainLayout.class)
public class ModeloEquipamentoList extends ListFormBase<ModeloEquipamento, ModeloEquipamentoService> {

    private static final long serialVersionUID = 2516403695651614441L;

    @Override
    protected void initViewComponents() {
        super.grid = new Grid<ModeloEquipamento>(ModeloEquipamento.class, false);
        grid.addColumns("nome", "marca.nome", "tipoEquipamento.nome", "obs");

        grid.getColumnByKey("nome").setHeader("Modelo");
        grid.getColumnByKey("marca.nome").setHeader("Marca");
        grid.getColumnByKey("tipoEquipamento.nome").setHeader("Tipo Equipamento");
        grid.getColumnByKey("obs").setHeader("Info. Adicionais");

        grid.addColumn(new ComponentRenderer<>(var -> {
            Checkbox ck = new Checkbox();
            ck.setValue(var.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");

    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(ModeloEquipamentoForm.class);

    }

}
