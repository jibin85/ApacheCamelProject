package com.example.cucumberDemo;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberOptions(features ={"src/test/java/com/example/cucumberDemo/productCheckout.feature"})
@RunWith(Cucumber.class)
public class ElectronicProdBddTest {

}
