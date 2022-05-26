package com.doiscs.sisman.views.equipamento;

import com.doiscs.sisman.domain.model.entity.Equipamento;
import com.doiscs.sisman.domain.services.EquipamentoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem de Equipamentos")
@Route(value = "equipamentolist", layout = MainLayout.class)
public class EquipamentoList extends ListFormBase<Equipamento, EquipamentoService> {

    private static final long serialVersionUID = 188115159646403753L;

    @Override
    protected void initViewComponents() {
        grid = new Grid<Equipamento>(Equipamento.class, false);

        grid.addColumns("nome", "ambiente.nome", "tipoEquipamento.nome", "marca.nome", "dataCompra", "garantiaAte",
                "ativo");
        grid.getColumnByKey("ambiente.nome").setHeader("Ambiente");
        grid.getColumnByKey("tipoEquipamento.nome").setHeader("Tipo do Equipamento");
        grid.getColumnByKey("marca.nome").setHeader("Marca");
    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(EquipamentoForm.class);
    }

}
