package com.example.cucumberDemo.processor;

import com.example.fileComponent.transformation.model.request.*;
import com.example.fileComponent.transformation.processor.InsuranceMappingXML;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class InsuranceMappingXMLTest extends CamelTestSupport {
    @Test
    public void positiveScenario() throws Exception {
        DefaultCamelContext defaultcamelContext = new DefaultCamelContext();
        DefaultExchange defaultExchange = new DefaultExchange(defaultcamelContext);
        Insurance insurance = new Insurance();
        CustomerDetails customerDetails = new CustomerDetails();
        Customer customer = new Customer();
        Premium premium = new Premium();
        Claim claim = new Claim();
        
        customer.setCustomerName("Jibin Thomas");
        customer.setCustomerId("A819557");
        customer.setCustomerDOB("21-02-1999");
        customerDetails.setCustomer(customer);
        
        premium.setPremiumAmount(new BigDecimal(5000.00));
        premium.setPremiumScheme("mediclaim");
        customerDetails.setPremium(premium);
        
        insurance.setCustomerDetails(customerDetails);
        defaultExchange.getIn().setBody(insurance);

        InsuranceMappingXML insuranceMappingXML = new InsuranceMappingXML();
        insuranceMappingXML.process(defaultExchange);
        assertNotNull(defaultExchange.getIn().getBody());
        
    }

    @Test
    public void positiveScenario1() throws Exception {
        DefaultCamelContext defaultcamelContext = new DefaultCamelContext();
        DefaultExchange defaultExchange = new DefaultExchange(defaultcamelContext);
        Insurance insurance = new Insurance();
        CustomerDetails customerDetails = new CustomerDetails();
        Customer customer = new Customer();
        Premium premium = new Premium();
        Claim claim = new Claim();

        customer.setCustomerName("Jibin Thomas");
        customer.setCustomerId("A819557");
        customer.setCustomerDOB("21-02-1999");
        customerDetails.setCustomer(customer);

        premium.setPremiumAmount(new BigDecimal(2000.00));
        premium.setPremiumScheme("mediclaim");
        customerDetails.setPremium(premium);

        insurance.setCustomerDetails(customerDetails);
        defaultExchange.getIn().setBody(insurance);

        InsuranceMappingXML insuranceMappingXML = new InsuranceMappingXML();
        insuranceMappingXML.process(defaultExchange);
        assertNotNull(defaultExchange.getIn().getBody());

    }

    @Test
    public void negativeScenario() throws Exception {
        DefaultCamelContext defaultcamelContext = new DefaultCamelContext();
        DefaultExchange defaultExchange = new DefaultExchange(defaultcamelContext);
        Insurance insurance = new Insurance();
        CustomerDetails customerDetails = new CustomerDetails();
        Customer customer = new Customer();
        Premium premium = new Premium();
        Claim claim = new Claim();

        customer.setCustomerName("Jibin Thomas");
        customer.setCustomerId("A819557");
        customer.setCustomerDOB("21-02-2009");
        customerDetails.setCustomer(customer);

        premium.setPremiumAmount(new BigDecimal(5000.00));
        premium.setPremiumScheme("mediclaim");
        customerDetails.setPremium(premium);

        insurance.setCustomerDetails(customerDetails);
        defaultExchange.getIn().setBody(insurance);

        InsuranceMappingXML insuranceMappingXML = new InsuranceMappingXML();
        insuranceMappingXML.process(defaultExchange);
        assertNotNull(defaultExchange.getIn().getBody());

    }

    @Test
    public void nullScenario() throws Exception {
        DefaultCamelContext defaultcamelContext = new DefaultCamelContext();
        DefaultExchange defaultExchange = new DefaultExchange(defaultcamelContext);
        Insurance insurance = new Insurance();
        CustomerDetails customerDetails = new CustomerDetails();
        Customer customer = new Customer();
        Premium premium = new Premium();
        Claim claim = new Claim();
        customerDetails.setCustomer(customer);
        customerDetails.setPremium(premium);
        insurance.setCustomerDetails(customerDetails);
        defaultExchange.getIn().setBody(insurance);
        InsuranceMappingXML insuranceMappingXML = new InsuranceMappingXML();
        insuranceMappingXML.process(defaultExchange);
        assertNotNull(defaultExchange.getIn().getBody());

    }
    
}
