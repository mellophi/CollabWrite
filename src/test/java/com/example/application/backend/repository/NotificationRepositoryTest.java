package com.example.application.backend.repository;


import com.example.application.backend.entity.Notification;
import com.example.application.backend.entity.Reflect;
import com.example.application.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class NotificationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void testCreatedNotification(){
        User user1 = entityManager.persist(new User("test1", "123"));
        User user2 = entityManager.persist(new User("test2", "123"));
        Reflect reflect = entityManager.persist(new Reflect("Writing this post for unit-testing", user1.getId(), user2.getId(),0));
        Notification notification = notificationRepository.save(new Notification("Writing this post for unit-testing", user1.getId(), user2.getId(),reflect.getId()));
        assertThat(notification).isNotNull();
        assertThat(notification.getId()).isGreaterThan(0);
    }
}
