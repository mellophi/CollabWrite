package com.example.application.backend.repository;

import com.example.application.backend.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {

//     @Query(value = "select * from history where post_id = ?1", nativeQuery = true)
     List<History> findAllByPostId(int postId);
}
