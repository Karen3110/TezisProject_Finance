package com.example.tezis.repository;

import com.example.tezis.dao.model.user.UserLoginPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginPasswordRepository extends JpaRepository<UserLoginPassword, Integer> {
    UserLoginPassword getByUsername(String userName);
}
