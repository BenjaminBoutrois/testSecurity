package com.objis.demospring.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.objis.demospring.security.domaine.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
	User findByUsername(String username);
}
