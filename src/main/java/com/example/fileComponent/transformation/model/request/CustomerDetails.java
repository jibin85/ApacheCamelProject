package com.example.fileComponent.transformation.model.request;

import lombok.Data;

@Data
public class CustomerDetails {
    private Customer customer;
    private Premium premium;
    private Claim claim;
}
