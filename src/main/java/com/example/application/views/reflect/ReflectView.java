package com.example.application.views.reflect;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.service.ReflectService;
import com.example.application.backend.service.UserService;
import com.github.appreciated.card.Card;
import com.github.appreciated.card.content.Item;
import com.github.appreciated.card.content.ItemBody;
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

    private List<Reflect> posts;
    private ReflectService reflectService;
    private UserService userService;

    public ReflectView(ReflectService reflectService, UserService userService) {
        addClassName("reflect-view");
        this.reflectService = reflectService;
        this.userService = userService;
//        add(new Text("Content placeholder"));
        updateCardList();
    }

    public void updateCardList(){
        posts = reflectService.FindPosts();
        for(Reflect post : posts){
            Div div = new Div();    // For using html
            div.getElement().setProperty("innerHTML", "<h3><strong>"+userService.FindByUserID(post.getUser_id())+"</strong></h3><hr>"
                                                        +post.getPost()+"<hr>"
                                                        +"<p style='font-size : 10px'><em>"+"posted on "+post.getPostDate()+"</em></p>");
            div.getStyle().set("padding", "20px");
            Card card = new Card(div);
            add(card);
        }
    }

}
