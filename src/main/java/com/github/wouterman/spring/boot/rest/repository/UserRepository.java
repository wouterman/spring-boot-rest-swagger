package com.github.wouterman.spring.boot.rest.repository;

import com.github.wouterman.spring.boot.rest.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
