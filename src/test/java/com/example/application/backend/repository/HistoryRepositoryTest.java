package com.example.application.backend.repository;

import com.example.application.backend.entity.History;
import com.example.application.backend.entity.Notification;
import com.example.application.backend.entity.Reflect;
import com.example.application.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class HistoryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void testCreatedReflect(){
        User user1 = entityManager.persist(new User("test1", "123"));
        User user2 = entityManager.persist(new User("test2", "123"));
        Reflect reflect = entityManager.persist(new Reflect("Writing this post for unit-testing", user1.getId(), user2.getId(),0));
        History history = historyRepository.save(new History(reflect.getPostDate(),"This is updated post for Unit Testing", user1.getId(),reflect.getId()));
        assertThat(history).isNotNull();
        assertThat(history.getId()).isGreaterThan(0);
    }
}
