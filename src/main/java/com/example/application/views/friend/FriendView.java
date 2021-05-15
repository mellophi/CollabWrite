package com.example.application.views.friend;

import com.example.application.backend.entity.History;
import com.example.application.backend.entity.User;
import com.example.application.backend.service.*;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.jsoup.Jsoup;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

@Route(value = "friend", layout = MainView.class)
@PageTitle("Friend")
public class FriendView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);
    private TextField searchText = new TextField();

    private String username;
    private ReflectService reflectService;
    private FriendService friendService;
    private UserService userService;
    private NotificationService notificationService;

    public FriendView(ReflectService reflectService, FriendService friendService, UserService userService, NotificationService notificationService){
        this.reflectService = reflectService;
        this.friendService = friendService;
        this.userService = userService;
        this.notificationService = notificationService;
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof UserDetails)
            username = ((UserDetails)principle).getUsername();
        else
            username = principle.toString();
        setSizeFull();
        configureFilter();
        configureGrid();
        updateList();
        add(searchText, grid);

    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.removeColumnByKey("id");
        grid.setColumns("username");

        grid.addColumn(user -> {
            return friendService.checkFriends(username, user.getId()) ? "Friend" : "Not friend";
        }).setHeader("Status");

        grid.asSingleSelect().addValueChangeListener(event -> sendFriendRequest(event.getValue()));
    }

    private void sendFriendRequest(User user) {
        if(friendService.checkFriends(username,user.getId())){
            Notification.show("Already Friend");
        }
        else{
            notificationService.saveNotification("Friend Request Incoming", user.getId(), reflectService.fetchUserId(username), -2);
            Notification.show("Friend request sent successfully !!");
        }
    }

    private void configureFilter() {
        searchText.setPlaceholder("Filter by name...");
        searchText.setClearButtonVisible(true);
        searchText.setValueChangeMode(ValueChangeMode.LAZY);
        searchText.addValueChangeListener(e -> updateList());
    }


    private void updateList(){
        grid.setItems(userService.findAllUsers(searchText.getValue(), username));
    }


}
