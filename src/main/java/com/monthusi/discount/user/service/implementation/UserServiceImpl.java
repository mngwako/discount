package com.monthusi.discount.user.service.implementation;

import com.monthusi.discount.user.entity.User;
import com.monthusi.discount.user.repository.IUserRepository;
import com.monthusi.discount.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository IUserRepository;

    @Override
    public User createUser(User user) {
        return IUserRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return IUserRepository.findAll();
    }

    @Override
    public User getUserByCustomerNumber(String customerNumber) {
        return IUserRepository.findUserByCustomerNumber(customerNumber);
    }

}
