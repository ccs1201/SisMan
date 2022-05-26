package com.doiscs.sisman.views.marca;

import com.doiscs.sisman.domain.model.entity.Marca;
import com.doiscs.sisman.domain.services.MarcaService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBaseGenerics;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cadasrtro Marca")
@Route(value = "marca", layout = MainLayout.class)

public class MarcaForm extends CadastroFormBaseGenerics<Marca, MarcaService> {

    /**
     *
     */
    private static final long serialVersionUID = -7824595258241000732L;
    private TextField nome;
    @PropertyId("ativo")
    private Checkbox ckAtivo;

    public MarcaForm() {

        super(Marca.class, "Marca");

    }

    /*
     * @Override protected boolean save() { String msg =
     * "Marca alterada com sucesso.";
     *
     * if (!isEditMode) { marca = new Marca(); msg =
     * "Marca cadastrada com sucesso."; }
     *
     * try { binder.writeBean(marca); service.save(marca); showSucess(msg); return
     * true;
     *
     * } catch (ValidationException e) {
     *
     * showWarnig("Verique os campos obrigatórios.");
     *
     * } catch (DataIntegrityViolationException e) {
     *
     * showWarnig("Marca já cadastrada, verique.");
     *
     * } return false;
     *
     * }
     *
     * @Override protected boolean delete() { boolean success = false;
     *
     * binder.writeBeanIfValid(marca);
     *
     * if (marca.getId() != null) { try { service.delete(marca); showSucess("Marca "
     * + marca.getNome() + " excluída com sucesso."); return true;
     *
     * } catch (Exception e) { showError("Erro ao excluir Marca.");
     *
     * super.logException(e, "erro no delete MarcaForm");
     *
     * }
     *
     * } else { showWarnig("Nenhum registro válido sendo editado.");
     *
     * }
     *
     * return success;
     *
     * }
     */

    @Override
    protected void initViewComponents() {
        nome = new TextField("Nome");
        ckAtivo = new Checkbox("Ativo ?", true);

        super.formLayout.add(ckAtivo, nome);

    }

    /*
     * @Override protected void initBinder() { binder = new
     * BeanValidationBinder<Marca>(Marca.class); binder.bindInstanceFields(this);
     *
     * }
     *
     * @Override protected void clearBinder() { marca = new Marca();
     * binder.readBean(marca); ckAtivo.setValue(true);
     *
     * }
     *
     * public void setEntitytoEdit(Marca entity) {
     *
     * try { this.marca = (Marca)
     * VaadinSession.getCurrent().getAttribute("entityToEdit"); } catch
     * (ClassCastException e) { showError(
     * "Isto não deveria acontecer. \n Objeto sendo editado não é uma Marca. \n" +
     * "Contatate o suporte."); }
     *
     * }
     *
     * @Override public void beforeEnter(BeforeEnterEvent event) {
     *
     * if (isEditMode) { configureToEdit(); }
     *
     * }
     *
     * @Override protected void configureToEdit() {
     *
     * this.marca = (Marca) getEntityToEdit();
     *
     * binder.readBean(marca);
     *
     * }
     */

    @Override
    protected void updateViewToEdit() {

        btnDelete.setVisible(true);
    }

    @Override
    protected void getNewEntityToPersist() {

        entity = new Marca();
    }

}
