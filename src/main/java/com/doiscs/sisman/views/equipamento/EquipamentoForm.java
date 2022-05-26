package com.doiscs.sisman.views.equipamento;

import com.doiscs.sisman.domain.model.entity.*;
import com.doiscs.sisman.domain.services.*;
import com.doiscs.sisman.views.MainLayout;
import com.doiscs.sisman.views.bases.CadastroFormBase;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
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

import java.util.Collection;

@PageTitle("Cadastro Equipamento")
@Route(value = "equipamento", layout = MainLayout.class)
public class EquipamentoForm extends CadastroFormBase<Equipamento> {

    private static final long serialVersionUID = 3118572104602973670L;
    @Autowired
    private EquipamentoService service;
    @Autowired
    private TipoEquipamentoService tipoEquipamentoService;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private EdificacaoService edificacaoService;

    @Autowired
    private AmbienteService ambienteService;

    private Equipamento equipamento;

    private Binder<Equipamento> binder;

    // containers
    FormLayout layout;

    // Componentes de tela
    @PropertyId("ativo")
    private Checkbox ckAtivo;

    @PropertyId("descricao")
    private TextArea txaDescricao;

    @PropertyId("tipoEquipamento")
    private ComboBox<TipoEquipamento> cbTipoEquipamento;

    @PropertyId("marca")
    private ComboBox<Marca> cbMarca;

    @PropertyId("ambiente")
    private ComboBox<Ambiente> cbAmbiente;

    @PropertyId("nome")
    private TextField txtNome;

    private DatePicker dataCompra;
    private DatePicker garantiaAte;

    private ComboBox<Edificacao> cbEdificacao;
    private ComboBox<UnidadeEdificacao> cbUnidadeEdificacao;

    public EquipamentoForm() {
        super(" ");

    }

    @Override
    protected void initViewComponents() {

        ckAtivo = new Checkbox("Ativo ?");
        ckAtivo.setValue(true);

        txtNome = new TextField("Nome Equipamento");

        txaDescricao = new TextArea("Descrição Equipamento");

        cbTipoEquipamento = new ComboBox<TipoEquipamento>("Tipo de Equipamento");
        cbTipoEquipamento.setItemLabelGenerator(TipoEquipamento::getNome);

        cbEdificacao = new ComboBox<Edificacao>("Edificação");
        cbEdificacao.setItemLabelGenerator(Edificacao::getNome);
        cbEdificacao.setPlaceholder("Selecione uma Edificação");
        cbEdificacao.addValueChangeListener(e -> updateCbUnidadeEdificacao());
        cbEdificacao.setClearButtonVisible(true);

        cbUnidadeEdificacao = new ComboBox<UnidadeEdificacao>("Unidade da Edificação");
        cbUnidadeEdificacao.setItemLabelGenerator(UnidadeEdificacao::getNome);
        cbUnidadeEdificacao.setPlaceholder("Selecione a Edificação");
        cbUnidadeEdificacao.setEnabled(false);
        cbUnidadeEdificacao.addValueChangeListener(e -> updateCbAmbiente());
        cbUnidadeEdificacao.setHelperText("Selecione uma Edificação para mostrar suas Unidades");

        cbMarca = new ComboBox<Marca>("Marca");
        cbMarca.setItemLabelGenerator(Marca::getNome);

        cbAmbiente = new ComboBox<Ambiente>("Ambiente");
        cbAmbiente.setItemLabelGenerator(Ambiente::getNome);
        cbAmbiente.setHelperText("Selecione uma Unidade para mostrar seus Ambientes");
        cbAmbiente.setEnabled(false);

        dataCompra = new DatePicker("Data da Compra");
        garantiaAte = new DatePicker("Garantia Até");


        layout = new FormLayout();
        layout.add(ckAtivo, 2);

        layout.add(cbTipoEquipamento, cbMarca, txtNome, cbEdificacao, cbUnidadeEdificacao, cbAmbiente, dataCompra,
                garantiaAte);

        layout.add(txaDescricao, 2);
        super.formLayout.add(layout);
    }

    private void updateCbAmbiente() {

        UnidadeEdificacao unidadeEdificacao = cbUnidadeEdificacao.getValue();

        if (unidadeEdificacao != null) {

            unidadeEdificacao.setAmbientes(ambienteService.findByUnidadeEdificacaoId(unidadeEdificacao.getId()));
            cbAmbiente.setItems(unidadeEdificacao.getAmbientes());
            cbAmbiente.setEnabled(true);
            cbAmbiente.setHelperText(null);

            if (unidadeEdificacao.getAmbientes() == null) {

                showWarnig("Nenhum Ambiente cadastrado para Unidade " + unidadeEdificacao.getNome()
                        + ", cadastre o Ambiente antes de cadastrar um equipamento.");
            }

        }
    }

    private void updateCbUnidadeEdificacao() {

        Edificacao edificacao = cbEdificacao.getValue();

        if (edificacao != null) {

            if (!edificacao.getUnidades().isEmpty()) {
                cbUnidadeEdificacao.setItems(cbEdificacao.getValue().getUnidades());
                cbUnidadeEdificacao.setEnabled(true);
                cbUnidadeEdificacao.setHelperText(null);
            } else {

                showWarnig("Nenhuma Unidade cadastrada para Edificação" + edificacao.getNome()
                        + ", cadastre uma unidade antes de cadastrar um Equipamento.");
            }
        } else {

            clearComboBoxes();
            disableComboboxes();
        }
    }

    private void disableComboboxes() {

        cbUnidadeEdificacao.setEnabled(false);

        cbAmbiente.setEnabled(false);

    }

    private void clearComboBoxes() {
        cbUnidadeEdificacao.clear();
        cbAmbiente.clear();

    }

    private Collection<Marca> getMarcas() {

        return marcaService.findAll();
    }

    private Collection<TipoEquipamento> getTipoEquipamento() {

        return tipoEquipamentoService.findAll();
    }

    @Override
    protected boolean delete() {

        try {

            service.delete(equipamento);
            showSucess("Equipamento: " + equipamento.getDescricao() + " excluído com sucesso.");

            return true;

        } catch (DataIntegrityViolationException e) {
            showWarnig("Equipamento não pode ser excluído pois está em uso.");
        } catch (Exception e) {
            showError("Erro ao excluir equipamento.");

            logException(e, "erro delete EquipamentoForm");
        }

        return false;
    }

    @Override
    protected boolean save() {

        String msg = "Equipamento alterado com sucesso.";

        if (this.equipamento == null) {
            msg = "Equipamento cadastrado com sucesso.";
            equipamento = new Equipamento();
        }

        try {

            binder.writeBean(equipamento);

            service.save(equipamento);
            showSucess(msg);
            return true;

        } catch (ValidationException e) {
            showWarnig("Verifique os campos obrigatórios.");

        } catch (DataIntegrityViolationException e) {

            showWarnig("Equipamento ja cadastrado para o ambiente.");
        }

        return false;

    }

    @Override
    protected void clearBinder() {
        equipamento = new Equipamento();
        binder.setBean(equipamento);
        updateViewPostSaveOrEdit();

    }

    private void updateViewPostSaveOrEdit() {
        btnDelete.setVisible(false);
        ckAtivo.setValue(true);

        // limpa os comboxoes
        cbUnidadeEdificacao.clear();
        cbAmbiente.clear();
        cbEdificacao.clear();

        // seta os comboboxes novamente para disable
        // garantindo o aninhamento das combos.
        cbUnidadeEdificacao.setEnabled(false);
        cbAmbiente.setEnabled(false);

    }

    @Override
    protected void initBinder() {
        binder = new BeanValidationBinder<Equipamento>(Equipamento.class);
        binder.bindInstanceFields(this);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        cbMarca.setItems(getMarcas());

        cbTipoEquipamento.setItems(getTipoEquipamento());

        cbEdificacao.setItems(getEdificacoes());

        if (isEditMode) {
            configureToEdit();

        }

    }

    private Collection<Edificacao> getEdificacoes() {

        return edificacaoService.findAtivos(true);
    }

    private void enbaleComboBoxes() {
        cbAmbiente.setEnabled(true);
        cbUnidadeEdificacao.setEnabled(true);

    }

    @Override
    protected void configureToEdit() {
        this.equipamento = (Equipamento) getEntityToEdit();

        enbaleComboBoxes();

        cbMarca.setValue(equipamento.getMarca());

        cbTipoEquipamento.setValue(equipamento.getTipoEquipamento());

        cbEdificacao.setValue(equipamento.getAmbiente().getUnidadeEdificacao().getEdificacao());

        updateCbUnidadeEdificacao();

        cbUnidadeEdificacao.setValue(equipamento.getAmbiente().getUnidadeEdificacao());

        updateCbAmbiente();

        cbAmbiente.setValue(this.equipamento.getAmbiente());
        updateCbAmbiente();

        binder.readBean(equipamento);

    }

}
