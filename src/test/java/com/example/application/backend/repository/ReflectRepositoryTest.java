package com.example.application.backend.repository;

import com.example.application.backend.entity.Reflect;
import com.example.application.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class ReflectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReflectRepository reflectRepository;

    @Test
    public void testCreatedReflect(){
        User user = entityManager.persist(new User("test", "123"));
        Reflect reflect = reflectRepository.save(new Reflect("Writing this post for unit-testing", user.getId(), user.getId(),0));
        assertThat(reflect).isNotNull();
        assertThat(reflect.getId()).isGreaterThan(0);
    }

}
