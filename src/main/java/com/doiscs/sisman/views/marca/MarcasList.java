package com.doiscs.sisman.views.marca;

import com.doiscs.sisman.domain.model.entity.Marca;
import com.doiscs.sisman.domain.services.impl.MarcaService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.ListFormBase;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Listagem de Marcas")
@Route(value = "marca-list", layout = MainLayout.class)
public class MarcasList extends ListFormBase<Marca, MarcaService> {

    private static final long serialVersionUID = 1L;

    @Override
    protected void initViewComponents() {

        grid = new Grid<Marca>(Marca.class, false);
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
        UI.getCurrent().navigate(MarcaForm.class);

    }

}

/*
 * protected void configureGrid() {
 *
 *
 *
 * grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
 * grid.setSelectionMode(SelectionMode.SINGLE); grid.getColumns().forEach(col ->
 * col.setAutoWidth(true)); grid.addItemClickListener(e ->
 * edit(grid.asSingleSelect().getValue()));
 *
 * grid.setColumns("nome");
 *
 * add(grid);
 *
 * }
 *
 * @Override protected void updateGrid() {
 *
 * if (txtpesquisa == null | txtpesquisa.isEmpty()) { //
 * System.out.println("FindaALL()"); grid.setItems(service.findAll());
 *
 * } else {
 *
 * grid.setItems(service.findByNome(txtpesquisa.getValue())); //
 * System.out.println("FindByNOME()");
 *
 * }
 *
 * }
 */