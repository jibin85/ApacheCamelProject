package com.example.fileComponent.transformation.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Premium {
    private String policyNumber;
    private BigDecimal premiumAmount;
    private String premiumScheme;
    private int validPeriod;
    private String validDate;
}
