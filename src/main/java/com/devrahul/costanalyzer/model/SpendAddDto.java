package com.devrahul.costanalyzer.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SpendAddDto {


    private Date spendingDate;

    private double spentAmount;

    private String spendType;

    private  String spendName;

    private  String spendCategory;

    private  String miscellaneousSpend;
}
