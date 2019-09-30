package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserPageRepository extends PagingAndSortingRepository<Users, Long> {
    Page<Users> findAll(Pageable pageable);

    Page<Users> findByFirstname(@RequestParam("firstname") String name, Pageable pageable);

    Page<Users> findByOccupation(@RequestParam("occupation") String occupation, Pageable pageable);
}
