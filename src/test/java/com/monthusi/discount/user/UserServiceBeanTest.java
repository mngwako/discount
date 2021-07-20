package com.monthusi.discount.user;

import com.monthusi.discount.sales.entity.SalesTransaction;
import com.monthusi.discount.user.entity.User;
import com.monthusi.discount.user.entity.UserType;
import com.monthusi.discount.user.repository.IUserRepository;
import com.monthusi.discount.user.service.implementation.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceBeanTest {

    @Mock
    private IUserRepository iUserRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldCreateUser(){
        String userType = "Employee";
        String dateString = "09/22/2006";
        Date registrationDate = formatDate(dateString);

        User expectedUser = buildUser(userType,registrationDate);

        when(iUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.createUser(expectedUser);
        assertEquals(expectedUser, actualUser);
        verify(iUserRepository).save(expectedUser);
    }

    @Test
    public void shouldGetAllUsers(){
        final List<User> expectedUsers = generateListOfUsers();

        when(iUserRepository.findAll()).thenReturn(expectedUsers);
        final List<User> actualUsers = userService.getAllUsers();

        verify(iUserRepository, times(1)).findAll();
        assertEquals(expectedUsers, actualUsers);
    }

    private User buildUser(String userType, Date registrationDate){
        User user = new User();
        user.setId("1");
        user.setCustomerNumber("0000001");
        user.setFullName("Test User");
        user.setUserType(buildUserType(userType));
        user.setRegistrationDate(registrationDate);
        return user;
    }

    private UserType buildUserType(String typeName){
        UserType userType = new UserType();
        userType.setId("1");
        userType.setName(typeName);
        return userType;
    }

    private Date formatDate(String date){
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date registrationDate =  new Date();
        try {
            registrationDate = dateStringFormat.parse(date);
        } catch(ParseException error) {
            System.out.println(error.toString());
        }
        return registrationDate;
    }

    private List<User> generateListOfUsers(){
        List<User> users = new ArrayList<>();

        User user1 = buildUser("Customer", formatDate("09/22/2006"));
        User user2 = buildUser( "Employee", formatDate("09/22/2021"));
        users.add(user1);
        users.add(user2);

        return users;
    }
}
