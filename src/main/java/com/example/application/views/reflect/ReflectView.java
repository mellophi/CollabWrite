package com.example.application.views.reflect;

import com.github.appreciated.card.Card;
import com.github.appreciated.card.content.Item;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;

@Route(value = "reflect", layout = MainView.class)
@PageTitle("Reflect")
@CssImport("./views/reflect/reflect-view.css")
public class ReflectView extends Div {

    public ReflectView() {
        addClassName("reflect-view");
//        add(new Text("Content placeholder"));
        Card card = new Card(new Item("Mello", "Hello"));
        add(card);
    }

}
