package com.example.application.views.friend;

import com.example.application.backend.entity.History;
import com.example.application.backend.entity.User;
import com.example.application.backend.service.FriendService;
import com.example.application.backend.service.HistoryService;
import com.example.application.backend.service.UserService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.jsoup.Jsoup;

import java.util.Collections;
import java.util.List;

@Route(value = "friend", layout = MainView.class)
@PageTitle("Friend")
public class FriendView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);

    private FriendService friendService;
    private UserService userService;
    public FriendView(FriendService friendService, UserService userService){
        this.friendService = friendService;
        this.userService = userService;
        setSizeFull();

        updateList();
        add(grid);

    }


    private void updateList(){
        grid.setItems(userService.findAllUsers());
    }


}
