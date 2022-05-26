package com.doiscs.sisman.views.equipamento;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.doiscs.sisman.domain.model.entity.Marca;
import com.doiscs.sisman.domain.model.entity.ModeloEquipamento;
import com.doiscs.sisman.domain.model.entity.TipoEquipamento;
import com.doiscs.sisman.domain.services.MarcaService;
import com.doiscs.sisman.domain.services.ModeloEquipamentoService;
import com.doiscs.sisman.domain.services.TipoEquipamentoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBase;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cadastro Modelo Equipamento")
@Route(value = "modeloequipamento", layout = MainLayout.class)
public class ModeloEquipamentoForm extends CadastroFormBase<ModeloEquipamento> {

    private static final long serialVersionUID = -5515074552183860407L;
    @Autowired
    private ModeloEquipamentoService service;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private TipoEquipamentoService tipoEquipamentoService;
    private ModeloEquipamento modeloEquipamento;

    Binder<ModeloEquipamento> binder;

    @PropertyId("marca")
    ComboBox<Marca> cbMarca;
    @PropertyId("nome")
    private TextField txtnome;
    @PropertyId("ativo")
    private Checkbox ckAtivo;
    @PropertyId("tipoEquipamento")
    private ComboBox<TipoEquipamento> cbTipoEquipamento;
    @PropertyId("obs")
    private TextArea txtObs;

    public ModeloEquipamentoForm() {
        super(" ");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        cbMarca.setItems(getMarcas());
        cbTipoEquipamento.setItems(getTipoEquipamentos());

        if (isEditMode) {
            configureToEdit();
        }

    }

    private Collection<TipoEquipamento> getTipoEquipamentos() {

        return tipoEquipamentoService.findAtivos(true);
    }

    protected void configureToEdit() {

        this.modeloEquipamento = (ModeloEquipamento) getEntityToEdit();
        binder.readBean(modeloEquipamento);

    }

    @Override
    protected boolean save() {

        String msg = "Modelo alterado com sucesso.";

        if (!isEditMode) {
            modeloEquipamento = new ModeloEquipamento();
            msg = "Modelo cadastrado com sucesso.";
        }

        try {
            binder.writeBean(modeloEquipamento);
            service.save(modeloEquipamento);
            showSucess(msg);
            return true;

        } catch (ValidationException e) {

            showWarnig("Verique os campos obrigatórios.");

        } catch (DataIntegrityViolationException e) {

            showWarnig("Modelo já cadastrado para este fabricante, verique.");

        }

        return false;

    }

    @Override
    protected boolean delete() {

        try {

            service.delete(modeloEquipamento);

            return true;

        } catch (DataIntegrityViolationException e) {
            showWarnig("Modelo não pode ser removido, pois esta em uso, consulte os equipamentos"
                    + " cadastrados para este modelo.");
        }

        return false;

    }

    @Override
    protected void initViewComponents() {
        initCbMarca();
        initTxtNnome();
        initCkAtivo();
        initCbTipoEquipamento();
        initTxtObs();

        super.formLayout.add(ckAtivo, cbMarca, cbTipoEquipamento, txtnome, txtObs);

    }

    private void initTxtObs() {
        txtObs = new TextArea("Informações adcionais do Modelo");
        txtObs.setMinWidth("35%");

    }

    private void initCbTipoEquipamento() {
        cbTipoEquipamento = new ComboBox<TipoEquipamento>("Tipo do Equipamento");
        cbTipoEquipamento.setItemLabelGenerator(TipoEquipamento::getNome);
        cbTipoEquipamento.setClearButtonVisible(true);
        cbTipoEquipamento.setPlaceholder("Selecione um tipo");
        cbTipoEquipamento.setMinWidth("35%");

    }

    private void initCkAtivo() {
        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);
    }

    private void initTxtNnome() {

        txtnome = new TextField("Modelo do Equipamento");
        txtnome.setMinWidth("35%");

    }

    private void initCbMarca() {
        cbMarca = new ComboBox<Marca>("Marca do Equipamento");
        cbMarca.setClearButtonVisible(true);

        cbMarca.setItemLabelGenerator(Marca::getNome);
        cbMarca.setPlaceholder("Selecione uma Marca");
        cbMarca.setMinWidth("35%");
    }

    private Collection<Marca> getMarcas() {

        return marcaService.findAtivos(true);
    }

    @Override
    protected void initBinder() {

        binder = new BeanValidationBinder<ModeloEquipamento>(ModeloEquipamento.class);
        binder.bindInstanceFields(this);

    }

    @Override
    protected void clearBinder() {

        binder.readBean(new ModeloEquipamento());
        ckAtivo.setValue(true);

    }

}
