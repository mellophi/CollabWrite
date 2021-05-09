package com.example.application.backend.service;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.entity.User;
import com.example.application.backend.repository.ReflectRepository;
import com.example.application.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.sql.Ref;
import java.util.List;
import java.util.Optional;

@Service
public class ReflectService {

    private ReflectRepository reflectRepository;
    private UserRepository userRepository;

    public ReflectService(ReflectRepository reflectRepository, UserRepository userRepository) {
        this.reflectRepository = reflectRepository;
        this.userRepository = userRepository;
    }

    public void savePost(int user_id, String post, int latest_user_id){
        reflectRepository.save(new Reflect(post, user_id, latest_user_id));
    }

    public int fetchUserId(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found "+username));
        return user.get().getId();
    }

    public List<Reflect> FindPosts(){
        return reflectRepository.findAll();
    }

    public Optional<Reflect> findPostById(int id) { return reflectRepository.findById(id); }

    public void updatePost(Reflect reflect) { reflectRepository.save(reflect); }
}
