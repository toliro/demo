package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.model.Users;
import com.example.demo.repository.TodoJpaRepository;
import com.example.demo.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    public List<Todo> findAll() {
        List<Todo> todo = todoJpaRepository.findAll();

        if (todo == null){
            throw new RuntimeException("Todos not found");
        }
        return todo;
    }

    public List<Todo> findByUsersId(Long id){
        if (id == null){
            return findAll();
        }
        List<Todo> todos = todoJpaRepository.findByUsersId(id);
        if (todos == null){
            throw new RuntimeException("Todos not found");
        }
        return todos;
    }

    public Todo getById(Long id) {
        Todo todo = todoJpaRepository.getById(id);
        if (todo == null){
            throw new RuntimeException("Todos not found");
        }
        return todo;
    }

    public Todo getByName(String name) {
        Todo todo = todoJpaRepository.findByName(name);
        if (todo == null){
            throw new RuntimeException("Todos not found");
        }
        return todo;
    }

    public String deleteTodo(Long id) {
        todoJpaRepository.deleteById(id);

        return "Todos not found";
    }

    public Todo load(Todo todo) {
        todoJpaRepository.save(todo);
        Todo todo1 = todoJpaRepository.findByName(todo.getName());
        if (todo == null){
            throw new RuntimeException("Todos not found");
        }
        return todo1;
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoJpaRepository.getById(id);
        if (todo == null){
            throw new RuntimeException("Todos not found");
        }

        todo.setName(todoDetails.getName());
        todo.setDescription(todoDetails.getDescription());
        todo.setStatus(todoDetails.getStatus());
        todo.setUsers(todoDetails.getUsers());

        Todo updatedTodo = todoJpaRepository.save(todo);
        if (updatedTodo == null){
            throw new RuntimeException("Todos not updated");
        }
        return updatedTodo;
    }
}
