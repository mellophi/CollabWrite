package com.example.application.views.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.Collections;

@Route("login")
@PageTitle("Login | CollabWrite")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");     // creating css class
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login.setAction("login");   // Sets the path where to send the form-data when a form is submitted
        login.setForgotPasswordButtonVisible(false);

        add(
                new H1("CollabWrite"),
                login,
                new RouterLink("Sign Up", SignupView.class)
        );

    }

    @Override
    //BeforeEnterObserver triggers an event before showing the page which is to be opened .
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(!beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .getOrDefault("error", Collections.emptyList())
                .isEmpty()){
            login.setError(true);
        }
    }
}
