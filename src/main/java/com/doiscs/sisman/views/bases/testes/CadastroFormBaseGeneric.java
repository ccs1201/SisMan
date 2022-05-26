package com.doiscs.sisman.views.bases.testes;

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
public abstract class CadastroFormBaseGeneric<T> extends FormLayout implements BeforeEnterObserver {

    /**
     *
     */
    private static final long serialVersionUID = 7069232922824142288L;

    private String nomeDoBean;

    private Button btnSave = new Button("Salvar");
    protected Button btnDelete = new Button("Excluir");
    private Button btnCancel = new Button("Cancelar");

    protected VerticalLayout formLayout = new VerticalLayout();
    ;

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    // public abstract void setEntitytoEdit(T entity);

    protected abstract void save();

    protected abstract void delete();

    protected abstract void initViewComponents();

    protected abstract void initBinder();

    public CadastroFormBaseGeneric(String nomeDoBean) {

        //System.out.println("Construtor form base....");
        /// this.nomeDoBean = nomeDoBean;

        init(nomeDoBean);

    }

    public CadastroFormBaseGeneric() {

        init(nomeDoBean);

        btnDelete.setVisible(true);

    }

    private void init(String nomeBean) {

        this.nomeDoBean = nomeBean;

        add(formLayout, 2);

        configureFormLayout();

        initViewComponents();

        setPlaceHolders(nomeDoBean);

        initBinder();

        postInitCheck();

    }

    private void postInitCheck() {

        if (formLayout.getComponentCount() < 1) {

            throw new RuntimeException("\n ###################################################################"
                    + "\n \n Você deve adicionar os componentes de tela \n"
                    + " através do método formLayout.add() da classe" + getClass().getSimpleName()
                    + "\n \n ###################################################################");
        }

        if (formLayout.getComponentAt(formLayout.getComponentCount() - 1) == null) {

            throw new RuntimeException("\n ###################################################################"
                    + "\n \n Você deve inicializar todos os componentes de tela \n"
                    + " através do método initViewComponents() da classe" + getClass().getSimpleName()
                    + "\n \n ###################################################################");

        }

    }

    protected void configureFormLayout() {

        // formLayout.setSizeFull();

        // formLayout.setMargin(true);
        formLayout.setSpacing(true);
        formLayout.setPadding(true);
        formLayout.setAlignItems(Alignment.AUTO);
        formLayout.setMargin(true);
        // add(formLayout, 2);

        configureButtonsLayout();

    }

    void configureButtonsLayout() {

        // btnDelete.setEnabled(false);
        btnDelete.setVisible(false);

        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        buttonsLayout = new HorizontalLayout();

        buttonsLayout.add(btnSave, btnDelete, btnCancel);
        buttonsLayout.setMargin(true);
        // buttonsLayout.setSpacing(true);
        buttonsLayout.setPadding(true);
        buttonsLayout.setSizeFull();

        configureButtonEvents();

        add(buttonsLayout);

    }

    private void configureButtonEvents() {

        btnSave.addClickListener(saveEvent -> save());
        btnDelete.addClickListener(deleteEvent -> delete());
        btnCancel.addClickListener(cancelEvent -> clearForm());

    }

    protected void clearForm() {

        btnDelete.setVisible(false);

        clearBinder();
        // removeBeanFromSession();

        /*
         * parentForm.closeDialog();
         *
         * if (parentForm != null) {
         *
         * parentForm.closeDialog();
         *
         * parentForm.updateGrid(); }
         */
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
            // System.out.println("no for o componente é: " + c.getClass().getSimpleName());

            if (c.getClass().getName().equals("com.vaadin.flow.component.textfield.TextField")) {

                // System.out.println("entrou no if " + c.getClass().getSimpleName());

                TextField aux = (TextField) c;
                aux.setPlaceholder(aux.getLabel() + " " + beanName + "...");

            }

        }
    }

    protected abstract void clearBinder();

    protected void showSucess(String message) {

        createNotification(message, NotificationVariant.LUMO_SUCCESS);

    }

    protected void showError(String message) {

        createNotification(message, NotificationVariant.LUMO_ERROR);

    }

    protected void showWarnig(String message) {
        createNotification(message, NotificationVariant.LUMO_CONTRAST);

    }

    private void createNotification(String message, NotificationVariant variant) {

        Notification n = new Notification(message);
        n.setDuration(7000);
        n.addThemeVariants(variant);
        n.setPosition(Position.TOP_CENTER);

        n.open();

    }

    protected void logException(Exception e, String logDesc) {

        System.out.println("################  " + logDesc.toUpperCase() + "  ################");

        e.printStackTrace();

    }

    protected Object getBeanToEdit() throws ClassCastException {

        if (VaadinServletRequest.getCurrent().getAttribute("entityToEdit") != null) {

            btnDelete.setVisible(true);
        }

        return VaadinServletRequest.getCurrent().getAttribute("entityToEdit");

    }
}
