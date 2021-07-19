package com.monthusi.discount.user.api;

import com.monthusi.discount.user.entity.User;
import com.monthusi.discount.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserApi {

    private final IUserService iUserService;

    @PostMapping(path = "/create")
    public User createUser (@RequestBody User user) {
        return iUserService.createUser(user);
    }

    @GetMapping(path = "/list/all")
    public List<User> getAllUsers () {
        return iUserService.getAllUsers();
    }
}
