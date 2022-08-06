package com.devrahul.costanalyzer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="USERS")
public class User {
    @Id
    @Column(name="USER_ID",unique = true,nullable = false)
    private  String userId;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL_ID",unique = true,nullable = false)
    private String emailId;


    @Column(name="USER_TYPE",nullable = false)
    private  String userType;


    @Column(name="USER_PASSWORD",nullable = false)
    private  String password;

    @Column(name="CREATED_DATE")
    private Date createDate;

    @Column(name="UPDATED_DATE")
    private  Date updateDate;


}
