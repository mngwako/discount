package com.monthusi.discount.user.repository;

import com.monthusi.discount.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);
}
