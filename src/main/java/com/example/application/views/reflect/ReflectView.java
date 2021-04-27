package com.example.application.views.reflect;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.service.ReflectService;
import com.example.application.backend.service.UserService;
import com.github.appreciated.card.Card;
import com.github.appreciated.card.content.Item;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "reflect", layout = MainView.class)
@PageTitle("Reflect")
@CssImport("./views/reflect/reflect-view.css")
public class ReflectView extends VerticalLayout {

    List<Reflect> posts;
    @Autowired
    ReflectService reflectService;

    @Autowired
    UserService userService;


    public ReflectView() {
        addClassName("reflect-view");
//        add(new Text("Content placeholder"));

        posts = reflectService.FindPosts();

        for(Reflect post : posts){
            Card card = new Card(new Item(userService.FindByUserID(post.getUser_id()) + " on " + post.getPostDate(), post.getPost()));
            add(card);
        }

    }

}
