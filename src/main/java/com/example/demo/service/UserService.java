package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserJpaRepository;
import com.example.demo.repository.UserPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserPageRepository userPageRepository;

    //getting users by Id
    public Users getById(Long id){
       Users user = userJpaRepository.getById(id);
        if (user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    //getting user by first name
    public Page<Users> getByFirstname(String name,Pageable pageable){
        pageable = PageRequest.of(0,10);
        Page<Users> page = userPageRepository.findByFirstname(name, pageable);
        if (page.hasContent()){
            return page;
        }else{
            throw new RuntimeException("User not found");
        }

    }

    //deleter user by id
    public void deleteUser(Long id){
        userJpaRepository.deleteById(id);
    }

    //update user by id
    public Users updateUser(Long id, Users usersDetails){
        Users users = userJpaRepository.getById(id);
        if (users == null){
            throw new RuntimeException("User not found");
        }
        users.setFirstname(usersDetails.getFirstname());
        users.setLastname(usersDetails.getLastname());
        users.setOccupation(usersDetails.getOccupation());
        users.setProfile_picture(usersDetails.getProfile_picture());

        Users updateUsers = userJpaRepository.save(users);
        return updateUsers;
    }

    //load users
    public Users load(Users users){
        userJpaRepository.save(users);
        Users user = userJpaRepository.findByFirstname(users.getFirstname());
        if (user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    //page - get all users
    public Page<Users> findAll(Pageable pageable){
        pageable = PageRequest.of(0,10, Sort.by("firstname"));
        Page<Users> page = userPageRepository.findAll(pageable);
        if (page.hasContent()){
            return page;
        }else{
            throw new RuntimeException("User not found");
        }
    }

    //page get user by occupation
    public Page<Users> findByOccupation(String occupation, Pageable pageable) {
        pageable = PageRequest.of(0,10, Sort.by("firstname"));
        Page<Users> page = userPageRepository.findByOccupation(occupation,pageable);

        if (page.hasContent()){
            return page;
        }else{
            throw new RuntimeException("User not found");
        }
    }
}
