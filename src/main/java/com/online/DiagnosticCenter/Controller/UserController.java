package com.online.DiagnosticCenter.Controller;

import com.online.DiagnosticCenter.Dto.UserDto;
import com.online.DiagnosticCenter.Entity.User;
import com.online.DiagnosticCenter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
@Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto) throws AuthenticationException {
        User user = userService.login(userDto.getUserName(), userDto.getPassword());
        UserDto userDto1 = new UserDto();
        userDto1.setUserType(user.getUserType());

        return userDto1;
    }

    @PostMapping("/Signup")
    public User signup(@RequestBody User user) {

        return userService.save(user);
    }



    @GetMapping("/getByUsername/{username}")
    public User getByUsername(@PathVariable String username) {

        return userService.getByUsername(username);
    }

    @GetMapping("/getAllUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/getInActiveUsers")
    public List<User> findInActiveEmployees() {
        return userService.findInActiveUsers();
    }

    @GetMapping("/getOneUser/{id}")
    public User findUser(@PathVariable("id") Long id) {

        return userService.findUser(id);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public User deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUserByUserName(id);
    }


    @GetMapping("/undoDelete/{id}")
    public boolean undoDelete(@PathVariable("id") Long id) {
        return userService.undoDeleteByUserName(id);
    }

}

