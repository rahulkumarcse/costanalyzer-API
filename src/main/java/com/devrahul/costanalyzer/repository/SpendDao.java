package com.devrahul.costanalyzer.repository;

import com.devrahul.costanalyzer.entity.UserSpendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpendDao extends JpaRepository<UserSpendsEntity, String> {

    @Query(value = "select * from USER_SPENDS WHERE USER_ID=?1",nativeQuery = true)
    public List<UserSpendsEntity> viewSpends(String userId);

}
