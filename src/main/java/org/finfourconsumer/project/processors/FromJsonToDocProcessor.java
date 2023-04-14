package org.finfourconsumer.project.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.finfourconsumer.project.model.Document;
import org.springframework.stereotype.Component;

@Component
public class FromJsonToDocProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = exchange.getMessage().getBody(String.class);
        Document deserializedDoc = objectMapper.readValue(json, Document.class);

        exchange.getIn().setBody(deserializedDoc);
    }
}
