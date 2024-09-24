package com.example.fileComponent.nonTransformation;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleFileMovement extends RouteBuilder {
    
    private static final String SOURCE = "{{source}}";
    private static final String DESTINATION = "{{destination}}";
    
    @Override
    public void configure() throws Exception {
        simpleSourceToDestination();
        filteredSourceToDestinationParticularFile();
        filteredSourceToDestinationByName();
        filteredSourceToDestinationByExtension();
    }

    private void simpleSourceToDestination() {
        from(SOURCE)
                .log(LoggingLevel.INFO, "Picked up file!")
                .log(LoggingLevel.INFO, "Incoming File is  : ${file:name}")
                .routeId("Simple-File-Movement-Route")
        .to(DESTINATION)
                .log(LoggingLevel.INFO, "Placed file!");
    }

    private void filteredSourceToDestinationParticularFile() {
        from(SOURCE+"ParticularFileInput/{{particularFileFilter}}")
                .log(LoggingLevel.INFO, "Picked up file!")
                .log(LoggingLevel.INFO, "Incoming File : ${file:name}")
                .routeId("Particular-File-Route")
        .to(DESTINATION)
                .log(LoggingLevel.INFO, "Placed file!");
    }

    private void filteredSourceToDestinationByName() {
        from(SOURCE+"FileNameAlone/{{fileFilterWithNoExtension}}")
                .log(LoggingLevel.INFO, "Picked up file!")
                .log(LoggingLevel.INFO, "Incoming File : ${file:name}")
                .routeId("File-Name-Route")
        .to(DESTINATION)
                .log(LoggingLevel.INFO, "Placed file!");
    }

    private void filteredSourceToDestinationByExtension() {
        from(SOURCE+"FileExtensionAlone/{{textFileFilter}}")
                .log(LoggingLevel.INFO, "Picked up file!")
                .log(LoggingLevel.INFO, "Incoming File is  : ${file:name}")
                .routeId("File-Extension-Route")
        .to(DESTINATION)
                .log(LoggingLevel.INFO, "Placed file!");
    }
}
