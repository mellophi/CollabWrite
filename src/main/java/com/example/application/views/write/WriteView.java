package com.example.application.views.write;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.service.HistoryService;
import com.example.application.backend.service.ReflectService;
import com.example.application.views.reflect.ReflectView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.router.*;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Route(value = "write", layout = MainView.class)
@PageTitle("Write")
@CssImport("./views/write/write-view.css")
@Component
public class WriteView extends VerticalLayout implements BeforeEnterObserver {

    private RichTextEditor name;
    private Button sayHello;
    private String user_name;
    private int user_id;
    private Optional<Reflect> reflect;

    private ReflectService reflectService;
    private HistoryService historyService;

    public WriteView(ReflectService reflectService, HistoryService historyService) {
        addClassName("write-view");
        this.reflectService = reflectService;
        this.historyService = historyService;
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
        if(reflect.get() == null){
            reflectService.savePost(user_id, name.getHtmlValue(), user_id);
            Notification.show("Post saved successfully!");
        }
        else{
            historyService.saveHistory(reflect.get().getPostDate(), reflect.get().getPost(), user_id);
            reflect.get().setPost(name.getHtmlValue());
            reflect.get().setLatest_user_id(user_id);
            reflect.get().setPostDate(LocalDate.now());
            reflectService.updatePost(reflect.get());
            Notification.show("Post updated successfully!");
        }
        UI.getCurrent().navigate(ReflectView.class);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        List<String> queryParameters = beforeEnterEvent.getLocation().getQueryParameters().getParameters().get("id");
        reflect = reflectService.findPostById(Integer.parseInt(queryParameters.get(0)));
        name.asHtml().setValue(reflect.get().getPost());
    }
}
