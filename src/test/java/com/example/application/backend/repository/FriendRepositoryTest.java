package com.example.application.backend.repository;

import com.example.application.backend.entity.Friend;
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
public class FriendRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FriendRepository friendRepository;

    @Test
    public void testCreatedFriend(){
        User user1 = entityManager.persist(new User("test1","123"));
        User user2 = entityManager.persist(new User("test2","123"));
        Friend friend = friendRepository.save(new Friend(user1.getId(), user2.getId()));
        assertThat(friend).isNotNull();
        assertThat(friend.getId()).isGreaterThan(0);
    }


}
