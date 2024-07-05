package com.doiscs.sisman.views.edificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.doiscs.sisman.domain.model.entity.TipoEdificacao;
import com.doiscs.sisman.domain.services.impl.TipoEdificacaoService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBase;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Tipo Edificação")
@Route(value = "tipoedificacao", layout = MainLayout.class)
public class TipoEdificacaoForm extends CadastroFormBase<TipoEdificacao> {

    private static final long serialVersionUID = 502295675321182436L;
    private TipoEdificacao tipoEdificacao;
    @Autowired
    private TipoEdificacaoService service;

    private Binder<TipoEdificacao> binder;

    // Componentes de Tela
    private TextField nome;
    private TextField descricao;
    private Checkbox isAtivo;

    public TipoEdificacaoForm() {
        super("tipo edificação");
        binder.forField(isAtivo).bind(TipoEdificacao::isAtivo, TipoEdificacao::setAtivo);
    }

    @Override
    protected void clearBinder() {
        tipoEdificacao = new TipoEdificacao();
        binder.readBean(tipoEdificacao);
    }

    @Override
    protected boolean delete() {
        try {
            binder.writeBean(tipoEdificacao);
            service.delete(tipoEdificacao);
            showSucess("Tipo de Edificação removido com sucesso.");
            return true;
        } catch (ValidationException e) {
            showError("Erro ao remover Tipo de Edificação.");
            e.printStackTrace();
        } catch (DataIntegrityViolationException e) {
            showWarnig("Tipo de Edificação não pode ser removido pois esta em uso.");
        }
        return false;
    }

    @Override
    protected boolean save() {
        String msg = "Tipo Edificação alterado com sucesso.";
        if (!isEditMode) {
            tipoEdificacao = new TipoEdificacao();
            msg = "Tipo Edificação cadastrado com sucesso.";
        }
        try {
            binder.writeBean(tipoEdificacao);
            service.save(tipoEdificacao);
            showSucess(msg);
            return true;

        } catch (ValidationException e) {
            showWarnig("Verique os campos obrigatórios.");
        } catch (DataIntegrityViolationException e) {
            showWarnig("Tipo Edificação já cadastrado, verique.");
        }
        return false;
    }

    @Override
    protected void initViewComponents() {
        nome = new TextField("Nome");
        descricao = new TextField("Descrição");
        isAtivo = new Checkbox("Ativo ?", true);
        super.formLayout.add(isAtivo, nome, descricao);
    }

    @Override
    protected void initBinder() {
        binder = new BeanValidationBinder<TipoEdificacao>(TipoEdificacao.class);
        binder.bindInstanceFields(this);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (isEditMode) {
            configureToEdit();
        }
    }

    @Override
    protected void configureToEdit() {
        this.tipoEdificacao = (TipoEdificacao) getEntityToEdit();
        binder.readBean(tipoEdificacao);
    }
}
