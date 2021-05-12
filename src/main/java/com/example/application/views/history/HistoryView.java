package com.example.application.views.history;

import com.example.application.backend.entity.History;
import com.example.application.backend.entity.User;
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

@Route(value = "history", layout = MainView.class)
@PageTitle("History")
public class HistoryView extends VerticalLayout implements BeforeEnterObserver {
    Grid<History> grid = new Grid<>(History.class);

    private HistoryService historyService;
    private UserService userService;
    public HistoryView(HistoryService historyService, UserService userService){
        this.historyService = historyService;
        this.userService = userService;
        setSizeFull();

        configureGrid();
        add(grid);

    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.removeColumnByKey("updatedPost");
        grid.setColumns("updatedPostDate", "postDate");

        grid.addColumn(history -> {
            return userService.findByUserID(history.getUserId());
        }).setHeader("Latest Update By");

        grid.addColumn(history -> {
            String post = history.getUpdatedPost();
            return Jsoup.parse(post).text();
        }).setHeader("Post Before Update");

        grid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void updateList(int postId){
        Notification.show(""+postId);
        grid.setItems(historyService.findAllByPostId(postId));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .getOrDefault("id", Collections.emptyList())
                .isEmpty()) {
           List<String> queryParameters = beforeEnterEvent.getLocation().getQueryParameters().getParameters().get("id");
           int postId = Integer.parseInt(queryParameters.get(0));
           Notification.show(""+ postId);
           updateList(postId);

        }
    }
}
