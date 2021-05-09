package com.example.application.views.friend;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "friend", layout = MainView.class)
@PageTitle("Friend")
public class FriendView extends VerticalLayout {
}
