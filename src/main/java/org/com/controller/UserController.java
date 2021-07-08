package org.com.controller;

import org.com.entity.User;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    public List<User> getAllUsers(){
        return userService.readAll();
    }

    public User getUserById(Long id){
        return userService.read(id);
    }

    public void saveUser(User user){
        userService.save(user);
    }

    public void editUser(User user){
        userService.edit(user);
    }

    public void deleteUser(Long id){
        userService.delete(id);
    }

    public User getUserByLogin(String login){
        return userService.readByLogin(login);
    }
}
