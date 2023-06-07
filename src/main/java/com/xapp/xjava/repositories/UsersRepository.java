package com.xapp.xjava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xapp.xjava.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    

    Optional<User> findByUserName(String userName);

    User findByEmail(String email);

}
