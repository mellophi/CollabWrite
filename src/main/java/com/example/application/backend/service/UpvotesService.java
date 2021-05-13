package com.example.application.backend.service;

import com.example.application.backend.entity.Upvotes;
import com.example.application.backend.repository.UpvotesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UpvotesService {
    private UpvotesRepository upvotesRepository;

    public UpvotesService(UpvotesRepository upvotesRepository) {
        this.upvotesRepository = upvotesRepository;
    }

    public List<Upvotes> findPostByIdAndUserId(int post_id, int userId) {
        return upvotesRepository.findByPostIdAndUserId(post_id, userId);
    }

    public void save(int post_id, int userId) {
        upvotesRepository.save(new Upvotes(post_id,userId));
    }
}

