package com.example.application.views.login;

import com.example.application.backend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;


@Route("sign-up")
@PageTitle("Sign-Up | CollabWrite")
public class SignupView extends VerticalLayout {
    TextField username = new TextField("Username");
    PasswordField password1 = new PasswordField("Password");
    PasswordField password2 = new PasswordField("Confirm Password");


    UserService userService;

    public SignupView(UserService userService){
        this.userService = userService;
        addClassName("sign-up-view");  // creating a css class(adding the classname for css modification)
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        add(
            new H1("Register"),
            username,
            password1,
            password2,
            new Button("Submit", event -> signup(
                    username.getValue(),
                    password1.getValue(),
                    password2.getValue()
            )),
            new RouterLink("Log in", LoginView.class)
        );
    }

    private boolean specialCharacterChecker(String password){
        boolean flag = false;
        Pattern regex = Pattern.compile("[^A-Za-z0-9]");
        for(int i = 0; i<password.length(); i++){
            char ch = password.charAt(i);
            if(regex.matcher("" + ch ).matches()){
                flag = true;
                break;
            }
        }
        return flag;
    }

    private void signup(String username, String password1, String password2) {
        Pattern regex = Pattern.compile("[^A-Za-z0-9]");
        if(username.trim().isEmpty()){
            Notification.show("Enter Username");
        }
        else if(password1.isEmpty()){
            Notification.show("Enter Password");
        }
        else if(!password1.equals(password2)){
            Notification.show("Password not matching");
        }
        else if(password1.length()<8 || !specialCharacterChecker(password1)){
            Notification.show("Please enter at least 8 character including one special character!");
        }
        else{
            if(userService.register(username, password1)){
                Notification.show("Registration Successfull");
                UI.getCurrent().navigate(LoginView.class);      // After successful signup redirects to login page
            }
            else Notification.show("Username already exists!!");
        }
    }
}

//package com.example.application.ui.views.list;
//
//        import com.example.application.backend.entity.Company;
//        import com.example.application.backend.entity.Contact;
//        import com.vaadin.flow.component.Component;
//        import com.vaadin.flow.component.ComponentEvent;
//        import com.vaadin.flow.component.ComponentEventListener;
//        import com.vaadin.flow.component.Key;
//        import com.vaadin.flow.component.button.Button;
//        import com.vaadin.flow.component.button.ButtonVariant;
//        import com.vaadin.flow.component.combobox.ComboBox;
//        import com.vaadin.flow.component.formlayout.FormLayout;
//        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//        import com.vaadin.flow.component.textfield.EmailField;
//        import com.vaadin.flow.component.textfield.TextField;
//        import com.vaadin.flow.data.binder.BeanValidationBinder;
//        import com.vaadin.flow.data.binder.Binder;
//        import com.vaadin.flow.shared.Registration;
//
//        import java.util.List;

//public class ContactForm extends FormLayout {
//
//    TextField firstName = new TextField("First name");
//
//
//    TextField lastName = new TextField("Last name");
//    EmailField email = new EmailField("Email");
//    ComboBox<Contact.Status> status = new ComboBox<>("Status");
//    ComboBox<Company> company = new ComboBox<>("Company");
//
//    Button save = new Button("Save");
//    Button delete = new Button("Delete");
//    Button close = new Button("Cancel");
//
//    Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);
//
//    public ContactForm(List<Company> companies) {
//        addClassName("contact-form");
//
//        binder.bindInstanceFields(this);
//        status.setItems(Contact.Status.values());
//        company.setItems(companies);
//        company.setItemLabelGenerator(Company::getName);
//
//        add(
//                firstName,
//                lastName,
//                email,
//                status,
//                company,
//                createButtonsLayout()
//        );
//    }
//
//    public void setContact(Contact contact){
//        binder.setBean(contact);
//    }
//
//    private Component createButtonsLayout() {
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
//        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//
//        save.addClickShortcut(Key.ENTER);
//        close.addClickShortcut(Key.ESCAPE);
//
//        save.addClickListener(click -> validateAndSave());
//        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
//        close.addClickListener(click -> fireEvent(new CloseEvent(this)));
//
//        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
//
//        return new HorizontalLayout(save, delete, close);
//    }
//
//    private void validateAndSave() {
//        if(binder.isValid()){
//            fireEvent(new SaveEvent(this, binder.getBean()));
//        }
//    }
//
//    // Events
//    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
//        private Contact contact;
//
//        protected ContactFormEvent(ContactForm source, Contact contact) {
//            super(source, false);
//            this.contact = contact;
//        }
//
//        public Contact getContact() {
//            return contact;
//        }
//    }
//
//    public static class SaveEvent extends ContactFormEvent {
//        SaveEvent(ContactForm source, Contact contact) {
//            super(source, contact);
//        }
//    }
//
//    public static class DeleteEvent extends ContactFormEvent {
//        DeleteEvent(ContactForm source, Contact contact) {
//            super(source, contact);
//        }
//
//    }
//
//    public static class CloseEvent extends ContactFormEvent {
//        CloseEvent(ContactForm source) {
//            super(source, null);
//        }
//    }
//
//    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
//                                                                  ComponentEventListener<T> listener) {
//        return getEventBus().addListener(eventType, listener);
//    }
//}
