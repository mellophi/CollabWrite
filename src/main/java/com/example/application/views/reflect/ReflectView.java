package com.example.application.views.reflect;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.service.ReflectService;
import com.example.application.backend.service.UserService;
import com.example.application.views.write.WriteView;
import com.github.appreciated.card.RippleClickableCard;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Route(value = "reflect", layout = MainView.class)
@PageTitle("Reflect")
@CssImport("./views/reflect/reflect-view.css")
public class ReflectView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    private WriteView writeView;

    private List<Reflect> posts;
    private ReflectService reflectService;
    private UserService userService;

    public ReflectView(ReflectService reflectService, UserService userService) {
        addClassName("reflect-view");
        this.reflectService = reflectService;
        this.userService = userService;
//        add(new Text("Content placeholder"));
        updateCardList(-1);
    }

    public void updateCardList(int postId){
//        Notification.show(""+postId);
        if(postId == -1) {
            posts = reflectService.FindPosts();
            for (Reflect post : posts) {
                configureCardList(post, 2);
            }
        }
        else {
            Reflect post = reflectService.findPostById(postId).get();
            configureCardList(post, 3);

        }
    }

    public void configureCardList(Reflect post, int layoutNumber){
        Div div = new Div();    // For using html
        div.getElement().setProperty("innerHTML", "<h3><strong>Posted by " + userService.findByUserID(post.getUser_id()) + "</h3></strong> latest update by "
                + userService.findByUserID(post.getLatest_user_id()) + "<hr>"
                + post.getPost() + "<hr>"
                + "<p style='font-size : 10px'><em>" + "posted on " + post.getPostDate() + " Upvote Count " + post.getUpvote() + "</em></p>");
        div.getStyle().set("padding", "20px");
        RippleClickableCard card = new RippleClickableCard(div);
        Component layout = (layoutNumber == 2 ) ? twoButtonLayout(post.getId()) : threeButtonLayout(post.getId());
        layout.setVisible(false);
        card.addClickListener(event -> {
            if (layout.isVisible())
                layout.setVisible(false);
            else
                layout.setVisible(true);
        });
        card.add(layout);
        add(card);
    }

    private Component twoButtonLayout(int post_id) {
        HorizontalLayout layout = new HorizontalLayout();
        Button history = new Button("History");
        Button edit = new Button("Edit");
        Button upvote = new Button("Like");
        edit.addClickListener(event -> {
            List<String> parameter = new ArrayList<>();
            parameter.add(String.valueOf(post_id));
            Map<String, List<String>> queryParameter = new HashMap<>();
            queryParameter.put("id", parameter);
            UI.getCurrent().navigate("write", new QueryParameters(queryParameter));
        });

        history.addClickListener(event -> {
            List<String> parameter = new ArrayList<>();
            parameter.add(String.valueOf(post_id));
            Map<String, List<String>> queryParameter = new HashMap<>();
            queryParameter.put("id", parameter);
           UI.getCurrent().navigate("history", new QueryParameters(queryParameter));
        });

        upvote.addClickListener(event ->{
            Reflect reflect = reflectService.findPostById(post_id).get();
            if(!userService.isUpvoted(reflect.getUser_id())){
                int upvoteCount = reflect.getUpvote();
                reflect.setUpvote(++upvoteCount);
                reflectService.updatePost(reflect);
                userService.updateUser(reflect.getUser_id());
                UI.getCurrent().getPage().reload();
            }
            else{
                Notification.show("Already liked the post");
            }

        });
        layout.add(history, edit, upvote);
        return layout;
    }

    private Component threeButtonLayout(int post_id) {
        HorizontalLayout layout = new HorizontalLayout();
        Button history = new Button("History");
        Button edit = new Button("Edit");
        Button ok = new Button("OK");
        edit.addClickListener(event -> {
            List<String> parameter = new ArrayList<>();
            parameter.add(String.valueOf(post_id));
            Map<String, List<String>> queryParameter = new HashMap<>();
            queryParameter.put("id", parameter);
            UI.getCurrent().navigate("write", new QueryParameters(queryParameter));
        });

        history.addClickListener(event -> {
            List<String> parameter = new ArrayList<>();
            parameter.add(String.valueOf(post_id));
            Map<String, List<String>> queryParameter = new HashMap<>();
            queryParameter.put("id", parameter);
            UI.getCurrent().navigate("history", new QueryParameters(queryParameter));
        });

        ok.addClickListener(event -> {
            List<String> parameter = new ArrayList<>();
            parameter.add(String.valueOf(-1));
            Map<String, List<String>> queryParameter = new HashMap<>();
            queryParameter.put("id", parameter);
            UI.getCurrent().navigate("reflect", new QueryParameters(queryParameter));
        });

        layout.add(history, edit, ok);
        return layout;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(!beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .getOrDefault("id", Collections.emptyList())
                .isEmpty()) {
            removeAll();
            List<String> queryParameters = beforeEnterEvent.getLocation().getQueryParameters().getParameters().get("id");
            updateCardList(Integer.parseInt(queryParameters.get(0)));
        }
    }
}

