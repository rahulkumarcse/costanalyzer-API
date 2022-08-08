package com.devrahul.costanalyzer.repository;

import com.devrahul.costanalyzer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserEntity,String> {

    @Query(value = "select * from USERS where EMAIL_ID =?1",nativeQuery = true)
    public UserEntity findByEmail(String emailId);

}
