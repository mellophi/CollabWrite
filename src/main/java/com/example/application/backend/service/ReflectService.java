package com.example.application.backend.service;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.entity.User;
import com.example.application.backend.repository.ReflectRepository;
import com.example.application.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReflectService {

    @Autowired
    ReflectRepository reflectRepository;

    @Autowired
    UserRepository userRepository;

    public void SavePost(int user_id, String post){
        reflectRepository.save(new Reflect(post, user_id));
    }

    public int fetchUserId(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found "+username));
        return user.get().getId();
    }

    public List<Reflect> FindPosts(){
        return reflectRepository.findAll();
    }
}
