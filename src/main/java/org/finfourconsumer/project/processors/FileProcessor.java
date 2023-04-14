package org.finfourconsumer.project.processors;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.finfourconsumer.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements org.apache.camel.Processor {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
//        producerTemplate.send("activemq:queue:FILE.OUTPUT",exchange);
//
//        Message message = exchange.getIn();
//        Date date = message.getHeader("CamelFileLastModified", Date.class);
//        String originDocName = message.getHeader("CamelFileName", String.class);
//        byte [] body = message.getBody(byte[].class);
//        long docNumber = 11111;
//        long docSum = 11111;
//        int userId = 11111;
//        String comment = "some comment";
//        String direction = "in or out";

        exchange.getIn().setHeader("UserId","1111111");
        exchange.getIn().setHeader("docNumber","111111111");
        exchange.getIn().setHeader("docSum","11111111");
        exchange.getIn().setHeader("comment","hardcode comment");
        exchange.getIn().setHeader("direction","hardcode direction");


        documentService.registerDocument(exchange);
    }
}
