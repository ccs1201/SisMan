package com.doiscs.sisman.views.bases;

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
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinServletRequest;

//@Route(layout = MainLayout.class)
public abstract class CadastroFormBase<T> extends FormLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = 7069232922824142288L;

    private String nomeDoBean;
    protected boolean isEditMode;

    private Button btnSave = new Button("Salvar");
    protected Button btnDelete = new Button("Excluir");
    private Button btnCancel = new Button("Cancelar");

    protected VerticalLayout formLayout = new VerticalLayout();
    ;

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    // public abstract void setEntitytoEdit(T entity);

    protected abstract boolean save();

    protected abstract boolean delete();

    protected abstract void initViewComponents();

    protected abstract void initBinder();

    public CadastroFormBase(String nomeDoBean) {

        init(nomeDoBean, 2);

    }

    private void init(String nomeBean, int colSpam) {

        this.nomeDoBean = nomeBean;

        initViewComponents();

        initBinder();

        setPlaceHolders(nomeDoBean);

        addComponentAsFirst(formLayout);
        configureFormLayout();

        checkEditMode();

    }

    /*
     * private void postInitCheck() {
     *
     * if (formLayout.getComponentCount() < 1) {
     *
     * throw new
     * RuntimeException("\n ###################################################################"
     * + "\n \n Você deve adicionar os componentes de tela \n" +
     * " através do método formLayout.add() da classe" + getClass().getSimpleName()
     * +
     * "\n \n ###################################################################");
     * }
     *
     * if (formLayout.getComponentAt(formLayout.getComponentCount() - 1) == null) {
     *
     * throw new
     * RuntimeException("\n ###################################################################"
     * + "\n \n Você deve inicializar todos os componentes de tela \n" +
     * " através do método initViewComponents() da classe" +
     * getClass().getSimpleName() +
     * "\n \n ###################################################################");
     *
     * }
     *
     * }
     */

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

        btnSave.addClickListener(saveEvent -> saveAndCleanForm());
        btnDelete.addClickListener(deleteEvent -> deleteAndCleanForm());
        btnCancel.addClickListener(cancelEvent -> clearForm());

    }

    private void deleteAndCleanForm() {

        if (delete()) {
            clearForm();
        }
    }

    private void saveAndCleanForm() {

        if (save()) {
            clearForm();
        }
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

    protected void setPlaceHolders(String beanName) {

        String primeiraLetra = "" + beanName.charAt(0);

        primeiraLetra = primeiraLetra.toUpperCase();
        char letraMaiuscula = primeiraLetra.charAt(0);

        char[] cArray = beanName.toCharArray();

        cArray[0] = letraMaiuscula;

        beanName = new String(cArray);

        for (int i = 0; i < formLayout.getComponentCount(); i++) {

            Component c = formLayout.getComponentAt(i);

            if (c.getClass().getName().equals("com.vaadin.flow.component.textfield.TextField")) {

                TextField aux = (TextField) c;
                aux.setPlaceholder(aux.getLabel() + " " + beanName + "...");
                aux.setClearButtonVisible(true);

            }

        }
    }

    protected abstract void clearBinder();

    protected abstract void configureToEdit();

    protected void showSucess(String message) {

        createNotification(message, NotificationVariant.LUMO_SUCCESS, 5000);

    }

    protected void showError(String message) {

        createNotification(message, NotificationVariant.LUMO_ERROR, 10000);

    }

    protected void showWarnig(String message) {
        createNotification(message, NotificationVariant.LUMO_CONTRAST, 10000);

    }

    private void createNotification(String message, NotificationVariant variant, int duration) {

        Notification n = new Notification(message);
        n.setDuration(7000);
        n.addThemeVariants(variant);
        n.setPosition(Position.TOP_CENTER);

        n.open();

    }

    protected void logException(Exception e, String logDesc) {

        System.out.println("################  " + logDesc.toUpperCase() + " $$ " + getClass().getSimpleName()
                + "  ################");

        e.printStackTrace();

    }

    protected Object getEntityToEdit() throws ClassCastException {

        if (isEditMode) {

            btnDelete.setVisible(true);
            return VaadinServletRequest.getCurrent().getAttribute("entityToEdit");
        }

        return null;

    }

    protected boolean checkEditMode() {

        if (VaadinServletRequest.getCurrent().getAttribute("entityToEdit") != null) {

            isEditMode = true;
        } else {
            isEditMode = false;
        }

        return isEditMode;

    }
}
