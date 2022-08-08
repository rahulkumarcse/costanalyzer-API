package com.devrahul.costanalyzer.repository;

import com.devrahul.costanalyzer.entity.UserSpendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendDao extends JpaRepository<UserSpendsEntity, String> {

}
