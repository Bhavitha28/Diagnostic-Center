package com.online.DiagnosticCenter.Repository;



import com.online.DiagnosticCenter.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.naming.AuthenticationException;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findByUserNameAndPassword(String username, String password);

    User findByUserName(String username);

    User findByIdAndIsActive(Long id, boolean b);

    List<User> findByIsActive(boolean b);

    List<User> findByUserType(String normal);
}