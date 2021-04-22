package com.example.application.views.write;

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

@Route(value = "write", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Write")
@CssImport("./views/write/write-view.css")
public class WriteView extends VerticalLayout {

    private TextArea name;
    private Button sayHello;

    public WriteView() {
        addClassName("write-view");
        name = new TextArea("Write your post");
        name.setSizeFull();
        sayHello = new Button("Submit post");
        add(name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
