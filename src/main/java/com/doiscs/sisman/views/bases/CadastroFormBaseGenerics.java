package com.doiscs.sisman.views.bases;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import com.doiscs.sisman.domain.services.ServiceInterface;
import com.doiscs.sisman.exceptions.DoisCsCrudException;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinServletRequest;

public abstract class CadastroFormBaseGenerics<T, SERVICE extends ServiceInterface<T>> extends FormLayout
        implements BeforeEnterObserver {

    protected T entity;
    @Autowired
    private SERVICE service;
    private final Class<T> beanClass;
    private Binder<T> binder;
    private final String nomeDoBean;
    private boolean isEditMode;
    private final Button btnSave = new Button("Salvar");
    protected Button btnDelete = new Button("Excluir");
    private final Button btnCancel = new Button("Cancelar");
    protected VerticalLayout formLayout = new VerticalLayout();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    protected CadastroFormBaseGenerics(Class<T> beanClass, String nomeDoBean) {
        this.beanClass = beanClass;
        this.nomeDoBean = nomeDoBean;
    }

    protected abstract void initViewComponents();

    @PostConstruct
    private void inicialize() {
        checkEditMode();
        initBinder();
        initViewComponents();
        addComponentAsFirst(formLayout);
        configureFormLayout();
        getEntityToEdit();
        if (isEditMode) {
            updateViewToEdit();
        }
    }


    protected void updateViewToEdit() {
        btnDelete.setVisible(true);
    }

    protected void initBinder() {
        binder = new BeanValidationBinder<T>(beanClass);
    }

    protected void setPlaceHolders(String beanName) {

        String primeiraLetra = "" + beanName.charAt(0);

        primeiraLetra = primeiraLetra.toUpperCase();
        char letraMaiuscula = primeiraLetra.charAt(0);

        char[] cArray = beanName.toCharArray();

        cArray[0] = letraMaiuscula;

        beanName = new String(cArray);

        for (int i = 0; i < formLayout.getComponentCount(); i++) {

            Component c = formLayout.getComponentAt(i);

            if (c instanceof com.vaadin.flow.component.textfield.TextField) {
                TextField tf = (TextField) c;
                tf.setPlaceholder(tf.getLabel() + " " + beanName + "...");
                tf.setClearButtonVisible(true);
            }
        }
    }

    protected void configureFormLayout() {

        formLayout.setSizeFull();

        // formLayout.setMargin(true);
        formLayout.setSpacing(true);
        formLayout.setPadding(true);
        formLayout.setAlignItems(Alignment.AUTO);
        formLayout.setMargin(true);
        // add(formLayout, 2);

        configureButtonsLayout();

    }

    void configureButtonsLayout() {

        btnDelete.setVisible(false);

        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        buttonsLayout = new HorizontalLayout();

        buttonsLayout.add(btnSave, btnDelete, btnCancel);
        buttonsLayout.setMargin(true);

        buttonsLayout.setPadding(true);
        buttonsLayout.setSizeFull();

        configureButtonEvents();

        add(buttonsLayout, 2);

    }

    private void configureButtonEvents() {

        btnSave.addClickListener(saveEvent -> save());
        btnDelete.addClickListener(deleteEvent -> delete());
        btnCancel.addClickListener(cancelEvent -> clearForm());

    }

    private void save() {

        String msg = nomeDoBean + " Alterada(o) com sucesso.";

        if (!isEditMode) {

            msg = nomeDoBean + " Cadastrada(o) com sucesso.";
            getNewEntityToPersist();

            // getNewEntity();

        }

        try {
            getBinder().writeBean(entity);
            service.save(entity);
            clearForm();
            showSucess(msg);

        } catch (DoisCsCrudException e) {

            showWarnig(nomeDoBean + " já cadastrado. Verique.");
            logException(e, "Metodo save()");

        } catch (ValidationException e) {

            showWarnig("Verique os campos obrigatórios.");

        }

    }

    /**
     * {@summary} Você deve implementar o {@code} entity = New POJO este método é
     * chamado toda vez que {@code} save() para criar um novo BEAN a ser persistido.
     */
    protected abstract void getNewEntityToPersist();

    private void delete() {
        try {
            service.delete(entity);
            clearForm();
            showSucess(nomeDoBean + " removida(o) com sucesso.");
        } catch (DoisCsCrudException e) {
            showWarnig(nomeDoBean + " não pode ser removida(o), pois existem registros que dependem dele. %/n "
                    + "Inative-o para que não seja mais exibido.");
        }
    }

    @SuppressWarnings("unchecked")
    private void getEntityToEdit() throws ClassCastException {

        if (isEditMode) {
            btnDelete.setVisible(true);
            this.entity = (T) VaadinServletRequest.getCurrent().getAttribute("entityToEdit");
        }
    }

    private boolean checkEditMode() {
        if (VaadinServletRequest.getCurrent().getAttribute("entityToEdit") != null) {
            isEditMode = true;
        } else {
            isEditMode = false;
        }
        return isEditMode;
    }

    /**
     * Sempre deve ser chamado após um click no botão salvar, cancelar ou excluir
     */
    protected void clearForm() {
        // Desabilita o botão para excluir um Bean Cadastrado
        btnDelete.setVisible(false);

        // Seta editmode para falso, garantido que a proxima interação
        // seja com um bean novo
        isEditMode = false;
        clearBinder();
    }

    protected void clearBinder() {
        binder.readBean(null);
    }

    protected void showSucess(String message) {
        createNotification(message, NotificationVariant.LUMO_SUCCESS, 5000);
    }

    protected void showError(String message) {
        createNotification(message, NotificationVariant.LUMO_ERROR, 5000);
    }

    protected void showWarnig(String message) {
        createNotification(message, NotificationVariant.LUMO_CONTRAST, 10000);
    }

    private void createNotification(String message, NotificationVariant variant, int duration) {
        Notification n = new Notification(message);
        n.setDuration(duration);
        n.addThemeVariants(variant);
        n.setPosition(Position.TOP_CENTER);
        n.open();
    }

    protected void logException(Exception e, String logDesc) {
        System.out.println("################  " + logDesc.toUpperCase() + " $$ " + getClass().getSimpleName()
                + "  ################");
        e.printStackTrace();

    }

    public Binder<T> getBinder() {
        return binder;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        binder.bindInstanceFields(this);
        if (entity != null) {
            getBinder().readBean(entity);
        }
    }
}
