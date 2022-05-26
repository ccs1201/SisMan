package com.doiscs.sisman.views.ambiente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doiscs.sisman.domain.model.entity.Ambiente;
import com.doiscs.sisman.domain.model.entity.Edificacao;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;
import com.doiscs.sisman.domain.services.AmbienteService;
import com.doiscs.sisman.domain.services.EdificacaoService;
import com.doiscs.sisman.domain.services.UnidadeEdificacaoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBaseGenerics;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cadastro Ambiente")
@Route(value = "ambiente", layout = MainLayout.class)
public class AmbienteForm extends CadastroFormBaseGenerics<Ambiente, AmbienteService> {

    private static final long serialVersionUID = -8621758791825558418L;
    Ambiente ambiente;
    @Autowired
    AmbienteService service;
    @Autowired
    UnidadeEdificacaoService unidadeEdificacaoService;
    @Autowired
    EdificacaoService edificacaoService;
    Binder<Ambiente> binder;

    @PropertyId("nome")
    private TextField txtNome;
    @PropertyId("descricao")
    private TextArea txtDescricao;
    @PropertyId("ativo")
    private Checkbox ckAtivo;
    @PropertyId("unidadeEdificacao")
    ComboBox<UnidadeEdificacao> cbUnidadeEdificacao;
    ComboBox<Edificacao> cbEdificacao;

    // container
    FormLayout layout;

    public AmbienteForm() {
        super(Ambiente.class, "Ambiente");

    }

    private void updateCbUnidadeEdificacao() {

        // IF garante q tenha um valor selecionado na cbEdificacao
        // para evitar nullpointerException nas combos aninhadas.
        if (cbEdificacao.getValue() != null) {
            cbUnidadeEdificacao.setItems(cbEdificacao.getValue().getUnidades());
        }

    }

    private void initComboxes() {

        cbEdificacao.setItems(edificacaoService.findAtivos(true));
        cbEdificacao.setItemLabelGenerator(Edificacao::getNome);

        cbEdificacao.addValueChangeListener(vchange -> updateCbUnidadeEdificacao());
    }

    @Override
    protected void initViewComponents() {

        txtDescricao = new TextArea("Descrição");
        txtDescricao.setMinWidth("35%");
        txtNome = new TextField("Nome Ambiente");
        txtNome.setMinWidth("35%");
        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);

        cbUnidadeEdificacao = new ComboBox<UnidadeEdificacao>("Unidade Edificação");
        cbUnidadeEdificacao.setPlaceholder("Selecione uma edificação..");
        cbUnidadeEdificacao.setMinWidth("35%");
        cbUnidadeEdificacao.setItemLabelGenerator(UnidadeEdificacao::getNome);

        cbEdificacao = new ComboBox<Edificacao>("Edificação");
        cbEdificacao.setPlaceholder("Selecione uma edificação");
        cbEdificacao.setMinWidth("35%");

        layout = new FormLayout();
        layout.add(ckAtivo, 2);

        layout.add(cbEdificacao, cbUnidadeEdificacao, txtNome, txtDescricao);
        super.formLayout.add(layout);

        initComboxes();

    }

    @Override
    protected void updateViewToEdit() {

        if (entity != null && entity.getId() != null) {

            List<UnidadeEdificacao> unidades = ambiente.getUnidadeEdificacao().getEdificacao().getUnidades();

            cbUnidadeEdificacao.setItems(unidades);

            cbEdificacao.setValue(ambiente.getUnidadeEdificacao().getEdificacao());

            binder.readBean(ambiente);
        }

    }

    @Override
    protected void getNewEntityToPersist() {
        entity = new Ambiente();

    }

}

/*
 * @Override protected boolean save() {
 *
 * String msg = "Ambiente alterado com sucesso.";
 *
 * if (!isEditMode) { ambiente = new Ambiente(); msg =
 * "Ambiente cadastrado com sucesso."; }
 *
 * try { binder.writeBean(ambiente); service.save(ambiente); showSucess(msg);
 *
 * return true;
 *
 * } catch (ValidationException e) {
 *
 * showWarnig("Verique os campos obrigatórios.");
 *
 * } catch (DataIntegrityViolationException e) {
 *
 * showWarnig("Ambiente já Cadastrado para esta edificação, verifique.");
 *
 * }
 *
 * return false;
 *
 * }
 *
 * @Override protected boolean delete() {
 *
 * try {
 *
 * service.delete(ambiente); showSucess("Ambiente removido com sucesso.");
 *
 * return true;
 *
 * } catch (DataIntegrityViolationException e) {
 *
 * showWarnig("Ambiente não pode ser removido pois está em uso.");
 *
 * } catch (Exception e) {
 *
 * showError("Erro ao remover Ambiente."); logException(e, "Erro no Delete()");
 * }
 *
 * return false;
 *
 * }
 */

/*
 * @Override protected void initBinder() { binder = new
 * BeanValidationBinder<Ambiente>(Ambiente.class);
 * binder.bindInstanceFields(this);
 *
 * }
 *
 * @Override protected void clearBinder() { this.ambiente = new Ambiente();
 * binder.readBean(ambiente); ckAtivo.setValue(true); cbEdificacao.clear();
 *
 * }
 */
