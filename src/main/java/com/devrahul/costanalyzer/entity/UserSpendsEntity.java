package com.devrahul.costanalyzer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_SPENDS")
public class UserSpendsEntity {
    @Id
    @GeneratedValue(generator = "uuid") //to generate string
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name ="SPEND_ID")
    private  String spendId;

    @Column(name = "SPENDING_DATE")
    private Date spendingDate;

    @Column(name = "ENTRY_DATE")
    private  Date entryDate;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "SPEND_AMOUNT")
    private double spentAmount;

    @Column(name = "SPEND_TYPE")
    private String spendType;

    @Column(name = "SPEND_NAME")
    private  String spendName;

    @Column(name = "SPEND_CATEGORY")
    private  String spendCategory;

    @Column(name="MISCELLANEOUS_SPEND")
    private  String miscellaneousSpend;
}
