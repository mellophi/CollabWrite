package com.example.application.backend.service;

import com.example.application.backend.entity.History;
import com.example.application.backend.entity.User;
import com.example.application.backend.repository.UserRepository;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public boolean register(String username, String password ) {
        if(userRepository.findByUsername(username).isPresent()){
            return false;
        }
        else {
            userRepository.save(new User(username, password));
            return true;
        }

    }

    public String findByUserID(int userid){
        return userRepository.findById(userid).get().getUsername();
    }

    public List<User> findAllUsers() {
        return  userRepository.findAll();
    }

    public List<User> findAllUsers(String searchText, String username) {
        if (searchText == null || searchText.isEmpty()) {
            return userRepository.findAllExceptUsernameLoggedIn(username);
        } else {
            return userRepository.search(searchText);
        }
    }
}
