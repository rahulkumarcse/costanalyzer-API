package com.devrahul.costanalyzer.service;

import com.devrahul.costanalyzer.entity.UserSpendsEntity;
import com.devrahul.costanalyzer.model.SpendAddDto;
import com.devrahul.costanalyzer.repository.SpendDao;
import com.devrahul.costanalyzer.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpendService {
    @Autowired
    public SpendDao spendDao;


    public List<Object> addSpending(String token, SpendAddDto newSpend) throws RollbackException {
        List<Object> result = new ArrayList<>();
        String userId = Security.getUserIdFromJwtToken(token);
        if (userId != null) {
            try {
                UserSpendsEntity userSpendsEntity = new UserSpendsEntity();
                userSpendsEntity.setSpendCategory(newSpend.getSpendCategory());
                userSpendsEntity.setSpendName(newSpend.getSpendName());
                userSpendsEntity.setSpendingDate(newSpend.getSpendingDate());
                userSpendsEntity.setSpendType(newSpend.getSpendType());
                userSpendsEntity.setUserId(userId);
                userSpendsEntity.setMiscellaneousSpend(newSpend.getMiscellaneousSpend());
                userSpendsEntity.setSpentAmount(newSpend.getSpentAmount());
                userSpendsEntity.setEntryDate(new Date());

                spendDao.save(userSpendsEntity);
                result.add(1);
                result.add(userSpendsEntity);
                return result;
            } catch (Exception e) {
                result.add(0);
                result.add(e.getMessage());
                return result;
            }

        } else {
            result.add(0);
            result.add("Please login using valid token");
            return result;

        }
    }

    public List<Object> modifySpend(String token, String spendId, SpendAddDto newSpend) {
        List<Object> result = new ArrayList<>();
        String userId = Security.getUserIdFromJwtToken(token);
        if (userId != null) {
            try {
                UserSpendsEntity userSpendsEntity = spendDao.getReferenceById(spendId);
                System.out.println(userSpendsEntity);

                String userIdFromSpend = userSpendsEntity.getUserId();
                if (userIdFromSpend.equals(userId)) {
                    userSpendsEntity.setSpendCategory(newSpend.getSpendCategory());
                    userSpendsEntity.setSpendName(newSpend.getSpendName());
                    userSpendsEntity.setSpendingDate(newSpend.getSpendingDate());
                    userSpendsEntity.setSpendType(newSpend.getSpendType());
                    userSpendsEntity.setMiscellaneousSpend(newSpend.getMiscellaneousSpend());
                    userSpendsEntity.setSpentAmount(newSpend.getSpentAmount());
                    spendDao.save(userSpendsEntity);
                    result.add(1);
                    result.add(userSpendsEntity);
                    return result;
                } else {
                    result.add(0);
                    result.add("Not authorised");
                    return result;
                }

            } catch (Exception e) {
                result.add(0);
                result.add(e.getMessage());
                return result;
            }

        } else {
            result.add(0);
            result.add("Please login using valid token");
            return result;

        }
    }

    public List<Object> viewSpend(String token) {
        List<Object> result = new ArrayList<>();
        String userId = Security.getUserIdFromJwtToken(token);
        if (userId != null) {
            List<UserSpendsEntity> userSpendsEntityList = spendDao.viewSpends(userId);
            result.add(1);
            result.add(userSpendsEntityList);
            return result;
        } else {
            result.add(0);
            result.add("Please authenticate using valid token");
            return result;
        }
    }
    public List<Object> removeSpend(String token,String spendId) {
        List<Object> result = new ArrayList<>();
        String userId = Security.getUserIdFromJwtToken(token);
        if (userId != null) {
            try {
                UserSpendsEntity userSpendsEntity = spendDao.getReferenceById(spendId);
                String userIdFromSpend = userSpendsEntity.getUserId();
                if (userIdFromSpend.equals(userId)) {

                    spendDao.delete(userSpendsEntity);
                    result.add(1);
                    result.add("Spend Deleted");
                    return result;
                } else {
                    result.add(0);
                    result.add("Not authorised");
                    return result;
                }

            } catch (Exception e) {
                result.add(0);
                result.add(e.getMessage());
                return result;
            }

        } else {
            result.add(0);
            result.add("Please login using valid token");
            return result;

        }
    }

    }
