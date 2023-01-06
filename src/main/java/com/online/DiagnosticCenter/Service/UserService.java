package com.online.DiagnosticCenter.Service;

import com.online.DiagnosticCenter.Entity.User;
import com.online.DiagnosticCenter.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) throws AuthenticationException {
        User user = userRepository.findByUserNameAndPassword(username, password);
        if (user == null) {
            throw new AuthenticationException();
        }
        return user;


    }
    public User save(User user) {

        return userRepository.save(user);
    }

        public User getByUsername (String username){
            return userRepository.findByUserName(username);
        }

    public List<User> findAllUsers() {
//        return userRepository.findByIsActive(true);
//        return userRepository.findAll();
        return  userRepository.findByUserType("Normal");

    }
    public List<User> findInActiveUsers() {
        return userRepository.findByIsActive(false);
    }



    public User updateUser(User user) {
        return userRepository.save(user);

    }

    public User findUser(Long id) {


        return userRepository.findByIdAndIsActive(id,true);
    }

    public User findInactiveUser(Long id) {


        return userRepository.findByIdAndIsActive(id,false);
    }


    public User deleteUserByUserName(Long id) {
        User user = this.findUser(id);
        user.setIsActive(false);
        this.userRepository.save(user);
        return user;
    }

    public boolean undoDeleteByUserName(Long id) {
        User user = this.findInactiveUser(id);
        user.setIsActive(true);
        User activatedUser = userRepository.save(user);

        if(activatedUser.getIsActive())
            return  true;
        return false;
    }



}
