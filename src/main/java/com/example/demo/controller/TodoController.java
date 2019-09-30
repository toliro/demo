package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> findAll(@RequestParam(required = false) final Long id){
        return todoService.findByUsersId(id);
    }

    @GetMapping(value = "/{id}")
    public Todo findById(@PathVariable final Long id){
        return todoService.getById(id);
    }

    @GetMapping(value = "/name/{name}")
    public Todo findByName(@PathVariable final String name){
        return todoService.getByName(name);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTodo(@PathVariable final Long id){
        todoService.deleteTodo(id);
    }

    @PostMapping(value = "")
    public Todo load(@RequestBody final Todo todo){
        return todoService.load(todo);
    }

    @PutMapping(value = "/{id}")
    public Todo updateTodo(@PathVariable final Long id, @Valid @RequestBody final Todo todo){
        return todoService.updateTodo(id, todo);
    }
}
