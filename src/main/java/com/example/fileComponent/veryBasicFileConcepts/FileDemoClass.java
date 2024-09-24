package com.example.fileComponent.veryBasicFileConcepts;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

//@Component
public class FileDemoClass extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        scenario1();
//        scenario2();
//        scenario3();
//        printMessageFromQ();
//        scenario5();
//        fileRenameRoute();
//        fileRenameWithCurrentTimeStampRoute();
//        directRouter();
        processedRouter();

    }

    private void fileRenameWithCurrentTimeStampRoute() {
            from("file:C:/Project/KT/input/")
                .routeId("Consume-AllFiles-Route")
                .log(LoggingLevel.INFO, "Incoming File : ${file:name}")
                .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}_${date:now:yyyyMMddhhmmss}.${file:name.ext}"))
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Renamed and Moved File Sucessfully as : ${file:name}");
    }

    private void fileRenameRoute() {
        from("file:C:/Project/KT/input/")
                .routeId("Consume-AllFiles-Route")
                .log(LoggingLevel.INFO, "Incoming File : ${file:name}")
                .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}_modified.${file:name.ext}"))
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Renamed and Moved File Sucessfully as : ${file:name}");
    }

    private void printMessageFromQ() {
        from("activemq:queue:sampleQ")
                .routeId("Amq-to-Local")
                .log(LoggingLevel.DEBUG, "Message From Queue : ${body}");
    }

    private void scenario5() {
        from("activemq:queue:DemoQ")
                .routeId("Amq-to-AMQ")
                .to("activemq:queue:DemoQ2")
                .log(LoggingLevel.DEBUG, "Successfully Sent msg to destination AMQ");
    }

    private void scenario1() {
        from("file:C:/Project/KT/input/")
                .routeId("Consume-AllFiles-Route")
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Moved File Sucessfully, Content is : ${body}");
    }

    private void scenario2() {
        from("file:C:/Project/KT/input/{{textFileFilter}}")
                .routeId("Filter-txtfile-Route")
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Moved File Sucessfully, Content is : ${body}");
    }

    private void scenario3() {
        from("file:C:/Project/KT/input/{{fileFilter}}")
                .routeId("Filter-Sample.txt-Route")
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Moved File Sucessfully, FileName: ${headers.CamelFileName}, Content is" +
                        " : ${body}");
    }

    private void directRouter() {
        from("file:C:/Project/KT/input/?fileName=sample.txt")
                .routeId("file-demo-1")
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Moved File Sucessfully, body : ${body}");
    }

    private void processedRouter() {
        from("file:C:/Project/KT/input/?fileName=sample2.txt")
                .routeId("file-demo-2")
                .process("fileProcessor")
                .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}_${date:now:yyyyMMddhhmmss}.${file:name.ext}"))
                .to("file:C:/Project/KT/output/")
                .log(LoggingLevel.INFO, "Moved File Sucessfully after transformation, body : ${body}");
    }
}
