package com.unified.service.dao;

import com.unified.service.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA
 * 
 * @author Abhishek
 *
 */
@Repository
public interface UserDao extends JpaRepository<UserModel, Integer>{

    // This Query will find record for given  username
    @Query("select u from UserModel u where u.userName= :name")
    public UserModel searchByName(@Param("name") String username);
}
