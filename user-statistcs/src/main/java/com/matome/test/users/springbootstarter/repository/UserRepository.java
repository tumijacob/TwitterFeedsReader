package com.matome.test.users.springbootstarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matome.test.users.springbootstarter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

