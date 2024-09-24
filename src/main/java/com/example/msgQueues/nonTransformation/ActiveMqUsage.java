package com.example.msgQueues.nonTransformation;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqUsage extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:ReceiverQ")
            .routeId("AMQ-to-console")
            .log(LoggingLevel.INFO, "Message From ReceiverQ : ${body}")
            .log(LoggingLevel.INFO, "Content : ${body}");

        from("timer:myTimer?period=60000")
                .routeId("producer-to-AMQ")
                .setBody(constant("Produced from timer component"))
                .to("activemq:queue:ReceiverQ");
        
//        from("activemq:queue:DemoQ")
//            .routeId("AMQ-to-file")
//            .setHeader(Exchange.FILE_NAME, simple("AMQ_Body.txt"))
//            .to("{{destination}}")
//            .log(LoggingLevel.INFO, "Content From ${headers.CamelJmsDestinationName} is written to ${header.CamelFileName} under {{destination}}")
//            .log(LoggingLevel.INFO, "Content : ${body}");
//
//        from("{{source}}?fileName=QueueContent.txt")
//            .routeId("file-to-AMQ")
//            .setHeader(Exchange.FILE_NAME, simple("AMQ_Body.txt"))
//            .to("activemq:queue:DemoQ1")
//            .log(LoggingLevel.INFO, "Content From ${header.CamelFileName} is sent to ${headers.CamelJmsDestinationName}")
//            .log(LoggingLevel.INFO, "Content : ${body}");
//
        from("activemq:queue:DemoQ1")
            .routeId("AMQ-to-AMQ")
            .to("activemq:queue:DemoQ2")
            .log(LoggingLevel.INFO, "Successfully Sent from DemoQ1 to DemoQ2")
            .log(LoggingLevel.INFO, "Content : ${body}");
    }
}
