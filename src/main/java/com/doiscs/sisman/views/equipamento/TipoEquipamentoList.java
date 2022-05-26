package com.doiscs.sisman.views.equipamento;

import com.doiscs.sisman.domain.model.entity.TipoEquipamento;
import com.doiscs.sisman.domain.services.TipoEquipamentoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem Tipo Equipamentos")
@Route(value = "tipoequipamentolist", layout = MainLayout.class)
public class TipoEquipamentoList extends ListFormBase<TipoEquipamento, TipoEquipamentoService> {


    private static final long serialVersionUID = 3784613160493727215L;

    @Override
    protected void initViewComponents() {

        grid = new Grid<TipoEquipamento>(TipoEquipamento.class, false);
        grid.addColumns("nome");

        grid.addColumn(new ComponentRenderer<>(var -> {
            Checkbox ck = new Checkbox();
            ck.setValue(var.isAtivo());
            ck.setReadOnly(true);
            return ck;
        })).setHeader("Ativo");

    }

    @Override
    protected void navigateTo() {
        UI.getCurrent().navigate(TipoEquipamentoForm.class);

    }

}
// Código transferido para Super Classe

/*
 * @Autowired private TipoEquipamentoService service;
 *
 * private Grid<TipoEquipamento> grid;
 *
 *
 * @Override public void updateGrid() {
 *
 * if (txtpesquisa == null | txtpesquisa.isEmpty()) {
 *
 * grid.setItems(service.findAll());
 *
 * } else {
 *
 * grid.setItems(service.findByDescricao(txtpesquisa.getValue())); }
 *
 * }
 *
 *
 *
 * protected void configureGrid() { grid = new
 * Grid<TipoEquipamento>(TipoEquipamento.class, false);
 *
 * grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
 * grid.setSelectionMode(SelectionMode.SINGLE); grid.getColumns().forEach(col ->
 * col.setAutoWidth(true)); grid.addItemClickListener(e ->
 * edit(grid.asSingleSelect().getValue()));
 *
 * grid.addColumn(TipoEquipamento::getDescricao).setHeader("Descrição").
 * setSortable(true);
 *
 *
 * add(grid);
 *
 * }
 */

/*
 * public void edit(AbstractEntity entity) {
 *
 * TipoEquipamentoForm form = new TipoEquipamentoForm(dialog, this,
 * (TipoEquipamento) entity, service);
 *
 * super.openDialogEdit(form, "Editar Tipo Equipamento"); }
 */
