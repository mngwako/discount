package com.monthusi.discount.user.service;

import com.monthusi.discount.user.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
}
