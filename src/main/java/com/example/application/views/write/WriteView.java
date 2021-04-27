package com.example.application.views.write;

import com.example.application.backend.service.ReflectService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

@Route(value = "write", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Write")
@CssImport("./views/write/write-view.css")
public class WriteView extends VerticalLayout {

    private TextArea name;
    private Button sayHello;
    private String user_name;
    private int user_id;

    @Autowired
    ReflectService reflectService;

    public WriteView() {
        addClassName("write-view");
        name = new TextArea("Write your post");
        name.setSizeFull();
        sayHello = new Button("Submit post");
        add(name, sayHello);
        sayHello.addClickListener(event -> submitPost());
    }

    private void submitPost() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
            user_name = ((UserDetails)principal).getUsername();
        else
            user_name = principal.toString();

//        Notification.show(user_name);
        user_id = reflectService.fetchUserId(user_name);
        reflectService.SavePost(user_id, name.getValue());
        Notification.show("Post saved successfully!");
    }

}
