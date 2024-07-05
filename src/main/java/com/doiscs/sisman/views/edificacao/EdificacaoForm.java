package com.doiscs.sisman.views.edificacao;

import com.doiscs.sisman.domain.model.entity.Edificacao;
import com.doiscs.sisman.domain.model.entity.TipoEdificacao;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;
import com.doiscs.sisman.domain.services.impl.EdificacaoService;
import com.doiscs.sisman.domain.services.impl.TipoEdificacaoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBase;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@PageTitle("Cadastro Edificação")
@Route(value = "edificacao", layout = MainLayout.class)
public class EdificacaoForm extends CadastroFormBase<Edificacao> {

    @Autowired
    private EdificacaoService service;
    @Autowired
    private TipoEdificacaoService tipoEdificacaoService;
    private Edificacao edificacao;

    private Binder<Edificacao> binder;

    // containers
    private Accordion accordion;
    private FormLayout layoutEdificacao;
    private VerticalLayout layoutUnidadesEdificacao;

    // Componentes de tela
    private Checkbox ckAtivo;
    @PropertyId("nome")
    private TextField txtNome;
    @PropertyId("descricao")
    private TextArea txtDescricao;
    @PropertyId("localizacao")
    private TextField txtLocalizacao;
    @PropertyId("tipoEdificacao")
    private ComboBox<TipoEdificacao> cbTipoEdificacao;
    private Grid<UnidadeEdificacao> gridUnidades;

    public EdificacaoForm() {
        super("Edificação");
    }

    @Override
    protected void initViewComponents() {

        accordion = new Accordion();
        accordion.setSizeFull();

        configureLayoutEdificacao();
        configureLayoutUnidadesEdificacao();

        super.formLayout.add(accordion);
    }

    private void configureLayoutUnidadesEdificacao() {
        layoutUnidadesEdificacao = new VerticalLayout();
        layoutUnidadesEdificacao.setSpacing(false);
        layoutUnidadesEdificacao.setMargin(false);
        layoutUnidadesEdificacao.setSizeFull();

        accordion.add("Unidades da Edificação", layoutUnidadesEdificacao);
    }

    private void configureLayoutEdificacao() {
        layoutEdificacao = new FormLayout();
        layoutEdificacao.setSizeFull();

        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);
        txtNome = new TextField("Nome");
        txtDescricao = new TextArea("Descrição");
        txtDescricao.setMaxHeight("150px");
        txtLocalizacao = new TextField("Localização");
        cbTipoEdificacao = new ComboBox<TipoEdificacao>("Tipo de Edificação");
        cbTipoEdificacao.setErrorMessage("Selecione o Tipo de Edificação.");
        cbTipoEdificacao.setRequired(true);

        layoutEdificacao.add(ckAtivo, 2);
        layoutEdificacao.add(cbTipoEdificacao, txtNome, txtLocalizacao, txtDescricao);
        accordion.add("Dados da Edificação", layoutEdificacao);
    }

    @Override
    protected boolean delete() {
        try {
            binder.writeBean(edificacao);
            service.delete(edificacao);
            showSucess("Edificação removido com sucesso.");
            return true;
        } catch (ValidationException e) {
            showError("Erro ao remover Edificação.");
            e.printStackTrace();
        } catch (DataIntegrityViolationException e) {
            showWarnig("A Edificação não pode ser removido pois esta em uso.");
        }
        return false;
    }

    @Override
    protected boolean save() {
        String msg = "Edificação alterado com sucesso.";

        if (!isEditMode) {
            edificacao = new Edificacao();
            msg = "Edificação cadastrado com sucesso.";
        }

        try {
            binder.writeBean(edificacao);
            service.save(edificacao);
            showSucess(msg);
            return true;
        } catch (ValidationException e) {
            showWarnig("Verique os campos obrigatórios.");
        } catch (DataIntegrityViolationException e) {
            showWarnig("Edificação já cadastrada, verique.");
        }
        return false;

    }

    @Override
    protected void clearBinder() {
        edificacao = new Edificacao();
        binder.readBean(edificacao);
        configureGridUnidades();
        ckAtivo.setValue(true);

    }

    @Override
    protected void initBinder() {
        binder = new BeanValidationBinder<Edificacao>(Edificacao.class);
        // Vincula compomenente ckBox ativo com getters e setters da Edificação
        binder.forField(ckAtivo).bind(Edificacao::isAtivo, Edificacao::setAtivo);
        binder.bindInstanceFields(this);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        cbTipoEdificacao.setItems(tipoEdificacaoService.findAtivos(true));
        cbTipoEdificacao.setItemLabelGenerator(TipoEdificacao::getNome);
        if (isEditMode) {
            configureToEdit();
        }
    }

    private void configureGridUnidades() {
        if (this.edificacao != null && this.edificacao.getUnidades() != null) {
            gridUnidades = new Grid<UnidadeEdificacao>(UnidadeEdificacao.class, false);
            gridUnidades.setItems(edificacao.getUnidades());
            gridUnidades.setColumns("nome", "ativo");
            gridUnidades.getColumnByKey("nome").setHeader("Nome Unidade");
            layoutUnidadesEdificacao.add(gridUnidades);
        } else {
            if (gridUnidades != null) {
                layoutUnidadesEdificacao.remove(gridUnidades);
            }
        }
    }

    @Override
    protected void configureToEdit() {
        this.edificacao = (Edificacao) getEntityToEdit();
        binder.readBean(edificacao);
        configureGridUnidades();
    }
}
