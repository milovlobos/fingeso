package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends JpaRepository<User, Long> {

    User findByUserEmail(String email);
}
