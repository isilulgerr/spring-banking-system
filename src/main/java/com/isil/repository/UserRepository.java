package com.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isil.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
