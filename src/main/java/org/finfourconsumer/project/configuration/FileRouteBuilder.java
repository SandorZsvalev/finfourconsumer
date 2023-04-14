package org.finfourconsumer.project.configuration;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        String url = "activemq:queue:FILE.OUTPUT";

        from(url)
                .routeId("docInputRoad")
                .process("fromJsonToDocProcessor")
                .process("storageProcessor")
                .stop();
    }
}
