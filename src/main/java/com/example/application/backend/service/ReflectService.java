package com.example.application.backend.service;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.entity.User;
import com.example.application.backend.repository.ReflectRepository;
import com.example.application.backend.repository.UserRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ReflectService {

    ReflectRepository reflectRepository;
    UserRepository userRepository;

    public void SavePost(int user_id, String post){
        reflectRepository.save(new Reflect(post, user_id));
    }

    public int fetchUserId(String user_name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(user_name);
        return user.getId();
    }
}
