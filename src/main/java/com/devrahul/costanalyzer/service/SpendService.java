package com.devrahul.costanalyzer.service;

import com.devrahul.costanalyzer.entity.UserSpendsEntity;
import com.devrahul.costanalyzer.model.SpendAddDto;
import com.devrahul.costanalyzer.repository.SpendDao;
import com.devrahul.costanalyzer.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpendService {
    @Autowired
    SpendDao spendDao;


    public List<Object> addSpending(String token, SpendAddDto newSpend) throws RollbackException {
        List<Object> result = new ArrayList<>();
        String userId = Security.getUserIdFromJwtToken(token);
        if(userId!=null){
            try{
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
            }
            catch (Exception e){
                result.add(0);
                result.add(e.getMessage());
                return result;
            }

        }
        else {
            result.add(0);
            result.add("Please login using valid token");
            return result;

        }
    }
}
