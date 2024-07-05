package com.doiscs.sisman.views.ambiente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doiscs.sisman.domain.model.entity.Ambiente;
import com.doiscs.sisman.domain.model.entity.Edificacao;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;
import com.doiscs.sisman.domain.services.impl.AmbienteService;
import com.doiscs.sisman.domain.services.impl.EdificacaoService;
import com.doiscs.sisman.domain.services.impl.UnidadeEdificacaoService;
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
    private Ambiente ambiente;
    @Autowired
    private AmbienteService service;
    @Autowired
    private UnidadeEdificacaoService unidadeEdificacaoService;
    @Autowired
    private EdificacaoService edificacaoService;
    private Binder<Ambiente> binder;

    @PropertyId("nome")
    private TextField txtNome;
    @PropertyId("descricao")
    private TextArea txtDescricao;
    @PropertyId("ativo")
    private Checkbox ckAtivo;
    @PropertyId("unidadeEdificacao")
    private ComboBox<UnidadeEdificacao> cbUnidadeEdificacao;
    private ComboBox<Edificacao> cbEdificacao;

    // container
    private FormLayout layout;

    public AmbienteForm() {
        super(Ambiente.class, "Ambiente");
    }

    @Override
    protected void initViewComponents() {

        txtDescricao = new TextArea("Descrição");
        txtDescricao.setMinWidth("35%");
        txtNome = new TextField("Nome Ambiente");
        txtNome.setMinWidth("35%");
        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);

        cbUnidadeEdificacao = new ComboBox<>("Unidade Edificação");
        cbUnidadeEdificacao.setPlaceholder("Selecione uma edificação..");
        cbUnidadeEdificacao.setMinWidth("35%");
        cbUnidadeEdificacao.setItemLabelGenerator(UnidadeEdificacao::getNome);

        cbEdificacao = new ComboBox<>("Edificação");
        cbEdificacao.setPlaceholder("Selecione uma edificação");
        cbEdificacao.setMinWidth("35%");

        layout = new FormLayout();
        layout.add(ckAtivo, 2);

        layout.add(cbEdificacao, cbUnidadeEdificacao, txtNome, txtDescricao);
        super.formLayout.add(layout);

        initComboxes();
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
        cbEdificacao.addValueChangeListener(e -> updateCbUnidadeEdificacao());
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