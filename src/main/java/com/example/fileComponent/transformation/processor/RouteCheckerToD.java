package com.example.fileComponent.transformation.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;


@Configuration("routeChecker")
public class RouteCheckerToD implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.setProperty("routeValue", false);
        if (Objects.nonNull(exchange.getIn().getBody())) {
            String routeDeterminor = exchange.getIn().getBody(String.class);
            System.out.println("Body of content before modifying: "+routeDeterminor);
            if (Objects.nonNull(routeDeterminor) && routeDeterminor.contains("B")) {
                exchange.setProperty("routeValue", "route3");
            } else if (Objects.nonNull(routeDeterminor) && routeDeterminor.contains("O")) {
                exchange.setProperty("routeValue", "route2");
            }else{
                System.out.println("Send appropriate route indicator");
                exchange.setProperty("routeValue", "invalidRouteIndicator");
            }
        } else {
            System.out.println("Body is null");
        }
    }
}
