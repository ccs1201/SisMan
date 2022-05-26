package com.doiscs.sisman.views.edificacao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.doiscs.sisman.domain.model.entity.Edificacao;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;
import com.doiscs.sisman.domain.services.EdificacaoService;
import com.doiscs.sisman.domain.services.UnidadeEdificacaoService;
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

@PageTitle("Unidade Edificação")
@Route(value = "unidadeedificacao", layout = MainLayout.class)
public class UnidadeEdificacaoForm extends CadastroFormBase<UnidadeEdificacao> {

    private static final long serialVersionUID = -2499126898733987248L;

    private UnidadeEdificacao unidadeEdificacao;
    @Autowired
    private UnidadeEdificacaoService service;
    @Autowired
    private EdificacaoService edService;
    private Binder<UnidadeEdificacao> binder;

    @PropertyId("ativo")
    private Checkbox ckAtivo;
    @PropertyId("nome")
    private TextField txtNome;
    @PropertyId("descricao")
    private TextArea txtDescricao;
    @PropertyId("edificacao")
    private ComboBox<Edificacao> cbEdificacao;

    public UnidadeEdificacaoForm() {
        super("Unidade Edificação");

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        cbEdificacao.setItems(getEdificacoes());
        cbEdificacao.setItemLabelGenerator(Edificacao::getNome);

        if (isEditMode) {
            configureToEdit();
        }

    }

    @Override
    protected boolean save() {
        String msg = "Unidade alterada com sucesso.";

        if (!isEditMode) {
            unidadeEdificacao = new UnidadeEdificacao();
            msg = "Tipo Edificação cadastrado com sucesso.";
        }

        try {
            binder.writeBean(unidadeEdificacao);
            service.save(unidadeEdificacao);
            showSucess(msg);

            return true;

        } catch (ValidationException e) {

            showWarnig("Verique os campos obrigatórios.");

        } catch (DataIntegrityViolationException e) {

            showWarnig("Unidade já cadastrada para esta edificação verique.");

            logException(e, "Erro método save()");
        }

        return false;

    }

    @Override
    protected boolean delete() {

        try {
            service.delete(unidadeEdificacao);

            showSucess("Unidade removida com sucesso.");

            return true;

        } catch (DataIntegrityViolationException e) {
            showWarnig(
                    "Unidade não pode ser removida pois esta em uso. Mova todos os ambientes para outra unidade antes de excluir.");
        }
        return false;

    }

    @Override
    protected void initViewComponents() {

        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);
        txtNome = new TextField("Nome Unidade");
        txtNome.setMinWidth("35%");
        txtDescricao = new TextArea("Descrição");
        txtDescricao.setMinWidth("35%");

        cbEdificacao = new ComboBox<Edificacao>("Edificação");
        cbEdificacao.setMinWidth("35%");

        super.formLayout.add(ckAtivo, cbEdificacao, txtNome, txtDescricao);

    }

    private Collection<Edificacao> getEdificacoes() {

        return edService.findAtivos(true);
    }

    @Override
    protected void initBinder() {
        binder = new BeanValidationBinder<UnidadeEdificacao>(UnidadeEdificacao.class);
        binder.bindInstanceFields(this);

    }

    @Override
    protected void clearBinder() {
        unidadeEdificacao = new UnidadeEdificacao();
        binder.readBean(unidadeEdificacao);
        ckAtivo.setValue(true);

    }

    @Override
    protected void configureToEdit() {

        this.unidadeEdificacao = (UnidadeEdificacao) getEntityToEdit();
        binder.readBean(unidadeEdificacao);

    }

}
