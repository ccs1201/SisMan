package com.doiscs.sisman.views.bases;

import org.springframework.beans.factory.annotation.Autowired;

import com.doiscs.sisman.domain.services.ServiceInterface;
import com.doiscs.sisman.exceptions.DoisCsViewExcepion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinServletRequest;

public abstract class ListFormBase<T, SERVICE extends ServiceInterface<T>> extends VerticalLayout {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected HorizontalLayout topo = new HorizontalLayout();
    protected TextField txtpesquisa = new TextField();
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnEditar = new Button("Editar");
    // protected Dialog dialog = new Dialog();
    protected Grid<T> grid;

    @Autowired
    protected SERVICE service;

    protected ListFormBase() {

        txtpesquisa.setPlaceholder("pesquisar por..");
        txtpesquisa.setClearButtonVisible(true);

        topo.setHeight("50px");
        topo.add(txtpesquisa, btnPesquisar, btnEditar);
        topo.setSpacing(true);

        this.add(topo);

        this.setSizeFull();

        btnPesquisar.addClickListener(e -> updateGrid());
        btnEditar.addClickListener(e -> edit());

        initViewComponents();

        configureGrid();

    }

    /**
     * Todos os componentes de UI devem ser inicializados dentro deste método.
     * <p>
     * {@code} initViewComponents
     * <p>
     * Isto garante que quando o construtor da superclasse chame os métodos
     * auxiliares não lance um {@code} NULLPointerException
     */

    protected abstract void initViewComponents();

    private void configureGrid() {

        if (grid == null) {

            throw new RuntimeException("\n ###################################################################"
                    + "\n \n Você deve inicializar a variável grid com o tipo específico de BEAN \n"
                    + " através do método initViewComponets( ) da classe" + getClass().getSimpleName()
                    + "\n \n ###################################################################");

        }

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        // grid.addItemClickListener(e -> edit(grid.asSingleSelect().getValue()));
        grid.setColumnReorderingAllowed(true);
        grid.getColumns().forEach(col -> col.setResizable(true));

        // grid.setColumns();

        add(grid);

    }

    protected void updateGrid() {

        if (txtpesquisa == null || txtpesquisa.isEmpty()) {
            // System.out.println("FindaALL()");
            grid.setItems(service.findAll());

        } else {

            grid.setItems(service.findByNome(txtpesquisa.getValue()));
            // System.out.println("FindByNOME()");

        }

    }

    protected void closeDialog() {
        // dialog.close();
    }

    /*
     * protected void editar() throws DoisCsViewExcepion {
     *
     *
     *
     * }
     */

    protected void edit() {

        try {
            VaadinServletRequest.getCurrent().setAttribute("entityToEdit", getEntityFromGrid());

            navigateTo();

        } catch (DoisCsViewExcepion e) {

            showWarnig(e.getLocalizedMessage());
        }
    }

    protected abstract void navigateTo();

    protected T getEntityFromGrid() throws DoisCsViewExcepion {

        if (grid.asSingleSelect().isEmpty()) {

            throw new DoisCsViewExcepion("Selecione um registro na lista para editar.");

        } else {

            return grid.asSingleSelect().getValue();

        }

    }

    protected void showSucess(String message) {

        createNotification(message, NotificationVariant.LUMO_SUCCESS);

    }

    protected void showError(String message) {

        createNotification(message, NotificationVariant.LUMO_ERROR);

    }

    protected void showWarnig(String message) {
        createNotification(message, NotificationVariant.LUMO_CONTRAST);

    }

    private void createNotification(String message, NotificationVariant variant) {

        Notification n = new Notification(message);
        n.setDuration(7000);
        n.addThemeVariants(variant);
        n.setPosition(Position.TOP_CENTER);

        n.open();

    }

}
