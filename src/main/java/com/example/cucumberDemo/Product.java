package com.example.cucumberDemo;

import io.cucumber.spring.CucumberContextConfiguration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@CucumberContextConfiguration
public class Product {

    Map<String, Integer> products;

    @PostConstruct
    public void setUp() {

        this.products = new ConcurrentHashMap<>();
    }

    public void givingThePrices(String product, int price) {
        System.out.println("giving the price " +product+ " is: " +price);
        this.products.put(product,price);
    }
    public int totalPrice(String product, int count) {
        System.out.println("total price " + product);
        return this.products.get(product) * count;

    }
}
