package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
    Todo getById(Long id);
    Todo findByName(String name);
    List<Todo> findByUsersId(@RequestParam Long id);
}
