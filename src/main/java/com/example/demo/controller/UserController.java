package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public Page<Users> findAll(@PageableDefault(page = 1, size = 10) Pageable pageable){
        return userService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public Users findById(@PathVariable final Long id){
        return userService.getById(id);
    }

    @GetMapping(value = "/name")
    public Page<Users> findByName(@RequestParam final String name,@PageableDefault(size = 10, page = 1) Pageable pageable){
        return userService.getByFirstname(name,pageable);
    }

    //pageable by occupation
    @GetMapping(value = "/search")
    public Page<Users> findByOccupation(@RequestParam(required = false) String occupation, @PageableDefault(size = 10, page = 1) Pageable pageable){
        return userService.findByOccupation(occupation, pageable);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable final Long id){
       userService.deleteUser(id);
    }

    @PostMapping(value = "")
    public Users load(@RequestBody final Users users){
        return userService.load(users);
    }

    @PutMapping(value = "/{id}")
    public Users updateUser(@PathVariable final Long id, @Valid @RequestBody final Users users){
        return userService.updateUser(id,users);
    }
}
