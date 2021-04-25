package com.example.application.backend.repository;

import com.example.application.backend.entity.Reflect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReflectRepository extends JpaRepository<Reflect, Integer> {

}
