package com.doiscs.sisman.views.equipamento;

import org.springframework.beans.factory.annotation.Autowired;

import com.doiscs.sisman.domain.model.entity.TipoEquipamento;
import com.doiscs.sisman.domain.services.impl.TipoEquipamentoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBaseGenerics;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Tipo do Equipamento")
@Route(value = "tipoequipamento", layout = MainLayout.class)
public class TipoEquipamentoForm extends CadastroFormBaseGenerics<TipoEquipamento, TipoEquipamentoService> {

    @Autowired
    private TipoEquipamentoService service;

    // componentes de tela
    @PropertyId("nome")
    private TextField txtNomeTipoEquipamento;
    @PropertyId("ativo")
    private Checkbox ckAtivo;

    public TipoEquipamentoForm() {

        super(TipoEquipamento.class, "tipo equipamento");

    }

    @Override
    protected void initViewComponents() {
        txtNomeTipoEquipamento = new TextField("Nome");
        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);

        super.formLayout.add(ckAtivo, txtNomeTipoEquipamento);

    }


    @Override
    protected void getNewEntityToPersist() {
        entity = new TipoEquipamento();

    }

    @Override
    protected void updateViewToEdit() {
        btnDelete.setVisible(true);
    }

    /*
     * @Override protected boolean delete() { if
     * (binder.writeBeanIfValid(tipoEquipamento)) {
     *
     * service.delete(tipoEquipamento);
     * showSucess("Tipo do equipamento removido com sucesso."); return true; } else
     * {
     *
     * showWarnig("Registro não pôde ser removido pois esta em uso."); }
     *
     * return false; }
     */

    /*
     * @Override protected boolean save() {
     *
     * String msg = "Tipo do equipamento alterado com sucesso.";
     *
     * if (!isEditMode) {
     *
     * tipoEquipamento = new TipoEquipamento(); msg =
     * "Tipo do equipamento cadastrado com sucesso."; }
     *
     * try {
     *
     * binder.writeBean(tipoEquipamento);
     *
     * service.save(tipoEquipamento); showSucess(msg); return true;
     *
     * } catch (ValidationException e) {
     *
     * showWarnig("Verifique os campos obrigatórios.");
     *
     * } catch (DataIntegrityViolationException e) {
     *
     * showWarnig(tipoEquipamento.getDescricao() + " já está cadastrado."); }
     *
     * return false; }
     */

    /*
     * @Override protected void clearBinder() {
     *
     * binder.readBean(new TipoEquipamento()); ckAtivo.setValue(true);
     *
     * }
     *
     * @Override protected void initBinder() { binder = new
     * BeanValidationBinder<TipoEquipamento>(TipoEquipamento.class);
     * binder.bindInstanceFields(this);
     *
     * }
     */

    /*
     * @Override public void beforeEnter(BeforeEnterEvent event) {
     *
     * if (isEditMode) {
     *
     * configureToEdit(); }
     *
     * }
     */

    /*
     * @Override protected void configureToEdit() { this.tipoEquipamento =
     * (TipoEquipamento) getEntityToEdit();
     *
     * binder.readBean(tipoEquipamento);
     *
     * }
     */


}
