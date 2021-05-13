package com.example.application.views.notification;

import com.example.application.backend.entity.Notification;
import com.example.application.backend.entity.Reflect;
import com.example.application.backend.service.FriendService;
import com.example.application.backend.service.NotificationService;
import com.example.application.backend.service.ReflectService;
import com.example.application.backend.service.UserService;
import com.example.application.views.write.WriteView;
import com.github.appreciated.card.Card;
import com.github.appreciated.card.RippleClickableCard;
import com.github.appreciated.card.content.Item;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "notification", layout = MainView.class)
@PageTitle("Notification")
public class NotificationView extends VerticalLayout {

    private List<Notification> notifications;
    private NotificationService notificationService;
    private UserService userService;
    private ReflectService reflectService;
    private FriendService friendService;

    public NotificationView(NotificationService notificationService,ReflectService reflectService,UserService userService, FriendService friendService) {
        addClassName("reflect-view");
        this.notificationService = notificationService;
        this.reflectService = reflectService;
        this.userService = userService;
        this.friendService = friendService;
        updateCardList();
    }

    public void updateCardList(){
        String username;
        int userId;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
            username = ((UserDetails)principal).getUsername();
        else
            username = principal.toString();
        userId = reflectService.fetchUserId(username);
        notifications = notificationService.findAllByNotifiedUserId(userId);


        for(Notification notification : notifications)
        {
            String notifiedByUserName = userService.findByUserID(notification.getNotifiedByUserId());
            RippleClickableCard card = new RippleClickableCard(new Item("Notified By " + notifiedByUserName , notification.getText()));
            Component layout = (notification.getPostId() == -2)? friendButtonLayout(notification.getPostId(), notification) : editButtonLayout(notification.getPostId(), notification);
            layout.setVisible(false);
            card.addClickListener(event -> {
                if(layout.isVisible())
                    layout.setVisible(false);
                else
                    layout.setVisible(true);
            });
            card.add(layout);
            add(card);
        }
    }



    private Component editButtonLayout(int postId, Notification notification) {
        HorizontalLayout layout = new HorizontalLayout();
        Button viewPost = new Button("View Post");
        Button ok = new Button("OK");
        viewPost.addClickListener(event -> {
            List<String> parameter = new ArrayList<>();
            parameter.add(String.valueOf(postId));
            Map<String, List<String>> queryParameter = new HashMap<>();
            queryParameter.put("id", parameter);
            UI.getCurrent().navigate("reflect", new QueryParameters(queryParameter));
            notificationService.deleteNotification(notification);
        });

        ok.addClickListener(event -> {
            notificationService.deleteNotification(notification);
            UI.getCurrent().getPage().reload();
        });
        layout.add(viewPost, ok);
        return layout;
    }

    private Component friendButtonLayout(int postId, Notification notification) {
        HorizontalLayout layout = new HorizontalLayout();
        Button acceptFriend = new Button("Accept Request");
        Button rejectFriend = new Button("Reject Request");
        acceptFriend.addClickListener(event -> {
            friendService.addFriend(notification.getNotifiedUserId(),notification.getNotifiedByUserId());
            UI.getCurrent().navigate("friend" );
            notificationService.deleteNotification(notification);
        });

        rejectFriend.addClickListener(event -> {
            notificationService.deleteNotification(notification);
            UI.getCurrent().getPage().reload();
        });
        layout.add(acceptFriend, rejectFriend);
        return layout;
    }


}

