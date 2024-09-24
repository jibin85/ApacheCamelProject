package com.example.fileComponent.transformation.route.xml_fl_concepts;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.example.fileComponent.transformation.dataFormat.BeanIODataFormatFactory.beanio;
import static com.example.fileComponent.transformation.dataFormat.JAXBDataFormatFactory.jaxb;

@Component
public class InsuranceRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        
        errorHandler(
            deadLetterChannel("direct:errorHandlerRoute")
            .maximumRedeliveries(2)
        );
        
        from("{{insuranceSource}}")
            .routeId("insurance-route")
            .log(LoggingLevel.INFO, "---------- STARTS ----------")
            .unmarshal(beanio("insuranceRequestStream", "classpath:beanio/insurance-mappings.xml"))
            .log(LoggingLevel.INFO, "Unmarshalled Successfully!")
            .multicast().parallelProcessing()
                .to("direct:toXmlEndpoint","direct:toFixedLengthEndpoint")
            .end()
            .log(LoggingLevel.INFO, "XML and FL delivered Successfully!")
            .log(LoggingLevel.INFO, "------------ ENDS ------------");
        
        from("direct:toXmlEndpoint")
            .routeId("insurance-endpoint1-route")
            .log(LoggingLevel.INFO, "---------- STARTS ----------")
            .process("insuranceMappingXML")
            .marshal(jaxb("com.insurance.xmlendpoint","classpath:xsd/validClaimSchema.xsd"))
            .log(LoggingLevel.INFO, "Marshalled Successfully!")
            .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}_ResXML.${file:name.ext}"))
            .to("{{xmlDestination}}")
            .log(LoggingLevel.INFO, "${header.CamelFileName} successfully placed under {{xmlDestination}}")
            .log(LoggingLevel.INFO, "------------ ENDS ------------");
                
        from("direct:toFixedLengthEndpoint")
            .routeId("insurance-endpoint2-route")
            .log(LoggingLevel.INFO, "---------- STARTS ----------")
            .process("insuranceMappingFL")
            .marshal(beanio("insuranceResponseStream", "classpath:beanio/insurance-mappings.xml"))
            .log(LoggingLevel.INFO, "Marshalled Successfully!")
            .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}_ResFL.${file:name.ext}"))
            .to("{{fixedLengthDestination}}")
            .log(LoggingLevel.INFO, "${header.CamelFileName} successfully placed under {{fixedLengthDestination}}")
            .log(LoggingLevel.INFO, "------------ ENDS ------------");
    }
}
