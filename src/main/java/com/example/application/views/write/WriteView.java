package com.example.application.views.write;

import com.example.application.backend.service.ReflectService;
import com.example.application.views.reflect.ReflectView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Ref;

@Route(value = "write", layout = MainView.class)
@PageTitle("Write")
@CssImport("./views/write/write-view.css")
public class WriteView extends VerticalLayout {

    private RichTextEditor name;
    private Button sayHello;
    private String user_name;
    private int user_id;

    private ReflectService reflectService;

    public WriteView(ReflectService reflectService) {
        addClassName("write-view");
        this.reflectService = reflectService;
        name = new RichTextEditor();
        name.setSizeFull();
        sayHello = new Button("Submit post");
        add(name, sayHello);
        sayHello.addClickListener(event -> submitPost());
    }

    private void submitPost() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  // get the credentials of logged in user
        if(principal instanceof UserDetails)
            user_name = ((UserDetails)principal).getUsername();
        else
            user_name = principal.toString();

        user_id = reflectService.fetchUserId(user_name);
        reflectService.SavePost(user_id, name.getHtmlValue());
        Notification.show("Post saved successfully!");
        UI.getCurrent().navigate(ReflectView.class);
    }

}
