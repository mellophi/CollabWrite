package com.example.application.backend.repository;

import com.example.application.backend.entity.Upvotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UpvotesRepository extends JpaRepository<Upvotes,Integer> {
    List<Upvotes> findAllByPostId(int post_id);
    @Query(value = "select * from upvotes where post_id = ?1 and user_id = ?2" , nativeQuery = true)
    List<Upvotes> findByPostIdAndUserId(int post_id, int userId);
}
