package com.example.fileComponent.veryBasicFileConcepts;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("fileProcessor")
public class SampleProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        String foreName = exchange.getIn().getBody(String.class);
        System.out.println("Body of content before modifying: "+foreName);
        String surName = "Ipilli";
        String fullName = surName.concat(" ").concat(foreName);
        System.out.println("After modification : "+fullName);
        exchange.getIn().setBody(fullName);
    }
}
