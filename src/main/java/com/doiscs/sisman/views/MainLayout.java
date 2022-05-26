package com.doiscs.sisman.views;

import java.util.ArrayList;
import java.util.List;

import com.doiscs.sisman.domain.model.entity.Fornecedor;
import com.doiscs.sisman.views.ambiente.AmbienteForm;
import com.doiscs.sisman.views.ambiente.AmbienteList;
import com.doiscs.sisman.views.edificacao.EdificacaoForm;
import com.doiscs.sisman.views.edificacao.EdificacaoList;
import com.doiscs.sisman.views.edificacao.TipoEdificacaoForm;
import com.doiscs.sisman.views.edificacao.TipoEdificacaoList;
import com.doiscs.sisman.views.edificacao.UnidadeEdificacaoForm;
import com.doiscs.sisman.views.edificacao.UnidadeEdificacaoList;
import com.doiscs.sisman.views.equipamento.EquipamentoForm;
import com.doiscs.sisman.views.equipamento.EquipamentoList;
import com.doiscs.sisman.views.equipamento.ModeloEquipamentoForm;
import com.doiscs.sisman.views.equipamento.ModeloEquipamentoList;
import com.doiscs.sisman.views.equipamento.TipoEquipamentoForm;
import com.doiscs.sisman.views.equipamento.TipoEquipamentoList;
import com.doiscs.sisman.views.fornecedor.FornecedorForm;
import com.doiscs.sisman.views.fornecedor.FornecedorList;
import com.doiscs.sisman.views.marca.MarcaForm;
import com.doiscs.sisman.views.marca.MarcasList;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;

/**
 * The main view is a top-level placeholder for other views.
 */
@PageTitle("Main")
public class MainLayout extends AppLayout {


    private static final long serialVersionUID = -4243594958791597867L;

    public static class MenuItemInfo {

        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("m-0", "text-l");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("SisMam");
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("list-none", "m-0", "p-0");
        nav.add(list);

        for (RouterLink link : createLinks()) {
            ListItem item = new ListItem(link);
            list.add(item);
        }
        return nav;
    }

    private List<RouterLink> createLinks() {
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                /*
                 * new MenuItemInfo("Home", "la la-file", HomeView.class), //
                 *
                 * new MenuItemInfo("Person Form", "la la-user", PersonFormView.class),
                 */

                new MenuItemInfo("Marca", "la la-file", MarcaForm.class),

                new MenuItemInfo("Listar Marcas", "la la-file", MarcasList.class),

                new MenuItemInfo("Tipo Equipamento", "la la-file", TipoEquipamentoForm.class),

                new MenuItemInfo("Listar Tipo Equipamento", "la la-file", TipoEquipamentoList.class),

                new MenuItemInfo("Tipo Edificação", "la la-file", TipoEdificacaoForm.class),

                new MenuItemInfo("Listar Tipo Edificação", "la la-file", TipoEdificacaoList.class),

                new MenuItemInfo("Edificação", "la la-file", EdificacaoForm.class),

                new MenuItemInfo("Listar Edificação", "la la-file", EdificacaoList.class),

                new MenuItemInfo("Unidade Edificação", "la la-file", UnidadeEdificacaoForm.class),

                new MenuItemInfo("Listar Unidades Edificação", "la la-file", UnidadeEdificacaoList.class),

                new MenuItemInfo("Ambiente Edificação", "la la-file", AmbienteForm.class),

                new MenuItemInfo("Listar Ambientes Edificação", "la la-file", AmbienteList.class),

                new MenuItemInfo("Modelo Equipamento", "la la-file", ModeloEquipamentoForm.class),

                new MenuItemInfo("Listar Modelos Equipamentos", "la la-file", ModeloEquipamentoList.class),

                new MenuItemInfo("Equipamento", "la la-file", EquipamentoForm.class),

                new MenuItemInfo("Listar Equipamento", "la la-file", EquipamentoList.class),

                new MenuItemInfo("Fornecedor", "la la-file", FornecedorForm.class),

                new MenuItemInfo("Listar Fornecedor", "la la-file", FornecedorList.class),

        };
        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());

        Span icon = new Span();
        icon.addClassNames("me-s", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s");

        link.add(icon, text);
        return link;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
