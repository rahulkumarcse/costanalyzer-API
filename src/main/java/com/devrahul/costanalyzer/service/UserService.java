package com.devrahul.costanalyzer.service;

import com.devrahul.costanalyzer.entity.UserEntity;
import com.devrahul.costanalyzer.model.LoginUserDTO;
import com.devrahul.costanalyzer.model.UserSignup;
import com.devrahul.costanalyzer.repository.UserDao;
import com.devrahul.costanalyzer.util.DataValidation;
import com.devrahul.costanalyzer.util.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userRepository;

    public List<Object> registerUser(UserSignup userSignup) {
        List<Object> regResult = new ArrayList<>();

        if (DataValidation.userNameValidation(userSignup.getUserId()) && DataValidation.emailValidation(userSignup.getEmailId()) && DataValidation.passwordValidation(userSignup.getPassword()) && DataValidation.nameValidation(userSignup.getName())) {
            if (!userRepository.existsById(userSignup.getUserId()) && userRepository.findByEmail(userSignup.getEmailId())==null) {
                try {
                    UserEntity newUser = new UserEntity();
                    newUser.setUserId(userSignup.getUserId());
                    newUser.setUserType("DEFAULT");
                    newUser.setName(userSignup.getName());
                    newUser.setEmailId(userSignup.getEmailId());
                    newUser.setPassword(Security.passwordEncoding(userSignup.getPassword()));
                    newUser.setCreateDate(new Date());
                    newUser.setUpdateDate(null);
                    userRepository.save(newUser);
                    regResult.add(1);
                    regResult.add(Security.jwtTokenGenerator(newUser.getUserId()));

                    return regResult;
                } catch (Exception e) {
                    e.printStackTrace();
                    regResult.add(0);
                    regResult.add(e.getMessage());
                    return regResult;
                }
            } else {
                regResult.add(0);
                regResult.add("Email OR USER ID already registered, Please try with different credentials");
            }

        }
        regResult.add(0);
        regResult.add("Please provide valid credentials to register");
        return regResult;
    }

    public List<Object> authUser(LoginUserDTO loginUserDTO) {
        List<Object> regResult = new ArrayList<Object>();
        if (  DataValidation.emailValidation(loginUserDTO.getEmail()) && DataValidation.passwordValidation(loginUserDTO.getPassword())) {
            try {
               if(!loginUserDTO.getEmail().contains("@") && userRepository.existsById(loginUserDTO.getEmail())){
                   UserEntity userById = userRepository.findById(loginUserDTO.getEmail()).get();
                   boolean passwordVerify = Security.passwordMatcher(loginUserDTO.getPassword(), userById.getPassword());
                   if (passwordVerify) {
                       String authToken = Security.jwtTokenGenerator(userById.getEmailId());
                       regResult.add(1);
                       regResult.add(authToken);
                       return regResult;
                   }
               }
               else if(loginUserDTO.getEmail().contains("@")){
                   UserEntity user = userRepository.findByEmail(loginUserDTO.getEmail());
                   if(user!=null){
                       boolean passwordVerify = Security.passwordMatcher(loginUserDTO.getPassword(), user.getPassword());
                       if (passwordVerify) {
                           String authToken = Security.jwtTokenGenerator(user.getEmailId());
                           regResult.add(1);
                           regResult.add(authToken);
                           return regResult;

                       }
                   }

               }
               else {
                   regResult.add(0);
                   regResult.add("Credentials doesn't match");
                   return regResult;
               }

            } catch (Exception e) {
                regResult.add(0);
                regResult.add(e.getMessage());
                return regResult;
            }
        }

        regResult.add(0);
        regResult.add("Please provide valid credentials to login");
        return regResult;

    }

}