package com.unified.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unified.service.model.User;

/**
 * Spring Data JPA
 * 
 * @author Abhishek
 *
 */
public interface UserDao extends JpaRepository<User, Integer>{

}
