package com.devrahul.costanalyzer.model;

import lombok.Data;

import java.util.Date;

@Data
public class SpendAddDto {


    private Date spendingDate;

    private double spentAmount;

    private String spendType;

    private  String spendName;

    private  String spendCategory;

    private  String miscellaneousSpend;
}
