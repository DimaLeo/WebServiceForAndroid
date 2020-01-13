package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    List<User> findAllByEmail(String email);
    List<User> findAllByUsername(String username);
    User findUserByEmail(String email);
    User findUserByUsername(String username);


}
