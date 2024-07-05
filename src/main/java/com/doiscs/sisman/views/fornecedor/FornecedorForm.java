package com.doiscs.sisman.views.fornecedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doiscs.sisman.domain.model.entity.Endereco;
import com.doiscs.sisman.domain.model.entity.Estado;
import com.doiscs.sisman.domain.model.entity.Fornecedor;
import com.doiscs.sisman.domain.model.entity.Municipio;
import com.doiscs.sisman.domain.services.impl.EstadoService;
import com.doiscs.sisman.domain.services.impl.FornecedorService;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBaseGenerics;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cadastro Forncedor")
@Route(layout = MainLayout.class)
public class FornecedorForm extends CadastroFormBaseGenerics<Fornecedor, FornecedorService> {

    private static final long serialVersionUID = -5599427454458715210L;

    EstadoService estadoService;

    @PropertyId("ativo")
    private Checkbox ckAtivo;

    @PropertyId("razaoSocial")
    private TextField txtRazaoSocial;

    @PropertyId("nomeFantasia")
    private TextField txtNomeFantasia;

    @PropertyId("cnpj")
    private TextField txtCnpj;

    @PropertyId("contato")
    private TextField txtContato;

    @PropertyId("telefoneContato")
    private TextField txtTelContato;

    @PropertyId("telefone")
    private TextField txtTelefone;

    @PropertyId("observacao")
    TextArea txtObservacao;

    private ComboBox<Estado> cbEstados;

    private ComboBox<Municipio> cbMunicipios;

    private TextField txtCep;

    private TextField txtLogradouro;

    private TextField txtNumero;

    private TextField txtComplemento;

    @PropertyId("emailContato")
    EmailField txtEmailContato;

    private FormLayout layout;

    HorizontalLayout layoutEstado;

    public FornecedorForm(@Autowired EstadoService estadoService) {
        super(Fornecedor.class, "Fornecedor");
        this.estadoService = estadoService;

        updateViewToEdit();

    }

    protected void configureAdditionalBinds() {

        getBinder().bind(cbEstados, "endereco.estado");
        getBinder().bind(cbMunicipios, "endereco.municipio");
        getBinder().bind(txtCep, "endereco.cep");
        getBinder().bind(txtLogradouro, "endereco.logradouro");
        getBinder().bind(txtNumero, "endereco.numero");
        getBinder().bind(txtComplemento, "endereco.complemento");

    }

    private void initCbEstados() {

        cbEstados = new ComboBox<Estado>("Estado");

        List<Estado> estados = estadoService.findAllEager();
        cbEstados.setItems(estados);

        cbEstados.addValueChangeListener(e -> updateCbMunicipios());

        cbEstados.setClearButtonVisible(true);

        cbEstados.setItemLabelGenerator(Estado::getNome);

        cbEstados.setWidth("50%");

    }

    private void initCbMunicpios() {

        cbMunicipios = new ComboBox<Municipio>("Município");
        cbMunicipios.setItemLabelGenerator(Municipio::getNome);
        cbMunicipios.setWidth("50%");
        cbMunicipios.setReadOnly(true);

    }

    private void updateCbMunicipios() {

        if (cbEstados.getValue() != null) {

            cbMunicipios.clear();

            cbMunicipios.setReadOnly(false);

            Estado estado = estadoService.findMunicipios(cbEstados.getValue());

            cbMunicipios.setItems(estado.getMunicipios());

        } else {
            cbMunicipios.clear();
            cbMunicipios.setReadOnly(true);
        }

    }

    @Override
    protected void initViewComponents() {

        layoutEstado = new HorizontalLayout();

        layout = new FormLayout();

        layoutEstado.setSizeFull();

        initCbEstados();
        initCbMunicpios();

        ckAtivo = new Checkbox("Ativo ?", true);
        txtRazaoSocial = new TextField("Razão Social");
        txtNomeFantasia = new TextField("Nome Fantasia");
        txtCnpj = new TextField("CNPJ");
        txtCnpj.setWidth("25%");
        // txtCnpj.setErrorMessage("CNPJ Incorreto.");
        // txtCnpj.setPattern("^[0-9]{2}?[-s.]?[0-9]{3}[-s.]?[0-9]{3}[-s/]?[0-9]{4}[-s-]?[0-9]{2}$");

        txtTelefone = new TextField("Telefone");

        txtContato = new TextField("Nome Contato");

        txtTelContato = new TextField("Telefone Contato");

        txtEmailContato = new EmailField("Email Contato");
        txtEmailContato.setClearButtonVisible(true);
        txtEmailContato.setErrorMessage("Formato do E-mail incorreto");
        txtEmailContato.setRequiredIndicatorVisible(false);

        txtLogradouro = new TextField("Logradouro");

        txtNumero = new TextField("Número");
        txtNumero.setWidth("50px");

        txtCep = new TextField("CEP");
        txtCep.setPattern("^[0-9]{5}?[-s-]?[0-9]{3}$");
        txtCep.setPlaceholder("00000-000");
        txtCep.setRequired(true);
        txtCep.setErrorMessage("Informe o CEP");

        txtCep.setWidth("75px");

        txtComplemento = new TextField("Complemento");

        txtObservacao = new TextArea("Observações");

        layout.add(ckAtivo, 2);
        layout.add(txtCnpj, 2);
        layout.add(txtRazaoSocial, txtNomeFantasia);

        layout.add(layoutEstado, 2);

        layout.add(txtLogradouro, txtNumero);
        layout.add(txtComplemento, txtCep, txtTelefone);

        HorizontalLayout layoutContato = new HorizontalLayout(txtContato, txtTelContato, txtEmailContato);
        layoutContato.setSizeFull();
        txtContato.setWidth("40%");
        txtEmailContato.setWidth("40%");
        txtTelContato.setWidth("20%");

        layout.add(layoutContato, 2);

        layout.add(txtObservacao, 2);

        super.formLayout.add(layout);

        layoutEstado.add(cbEstados, cbMunicipios);

        configureAdditionalBinds();

    }

  /*  protected void updateViewToEdit() {

        Estado estado = entity.getEndereco().getEstado();
        estado = estadoService.findMunicipios(estado);

        cbEstados.setValue(estado);

        btnDelete.setVisible(true);

    }*/

    @Override
    protected void getNewEntityToPersist() {

        entity = new Fornecedor();
        entity.setEndereco(new Endereco());
        ckAtivo.setValue(true);

    }

}
