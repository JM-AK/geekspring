package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
}
