package com.devrahul.costanalyzer.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSignup {
    private  String userId;
    private String emailId;
    private  String name;
    private  String password;
}
