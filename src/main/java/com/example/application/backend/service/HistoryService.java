package com.example.application.backend.service;

import com.example.application.backend.entity.History;
import com.example.application.backend.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    public void saveHistory(LocalDate post_date, String post, int user_id){
        historyRepository.save(new History(post_date, post, user_id));
    }
}
