package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String theRoleName);
}
