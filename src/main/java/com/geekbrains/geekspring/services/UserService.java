package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.SystemUser;
import com.geekbrains.geekspring.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);

    void save(User user);
    
    User findById(Long id);

    void deleteById(Long id);

    List<User> findAll();
}
