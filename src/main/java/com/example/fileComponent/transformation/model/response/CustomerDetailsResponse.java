package com.example.fileComponent.transformation.model.response;

import lombok.Data;

@Data
public class CustomerDetailsResponse {
    private String validClaim;
    private CustomerResponse customer;
    private ClaimResponse claim;
}
