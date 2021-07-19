package com.monthusi.discount.user.service.implementation;

import com.monthusi.discount.user.entity.User;
import com.monthusi.discount.user.repository.UserRepository;
import com.monthusi.discount.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findUserById(id);
    }

}
