package org.finfourconsumer.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.finfourconsumer.project.model.Document;
import org.finfourconsumer.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    CamelContext camelContext;

    @Autowired
    DocumentService documentService;

    @PostMapping
    public ResponseEntity<?> sendDocument(@RequestBody Document document) throws JsonProcessingException {
        Exchange exchange = camelContext.createProducerTemplate().send("direct:documentInput", ExchangePattern.InOnly,
                new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(document);
                    }
                });
        ObjectMapper objectMapper = new ObjectMapper();
        String json = exchange.getMessage().getBody(String.class);
        Document deserializedDoc = objectMapper.readValue(json, Document.class);

        return ResponseEntity.ok(deserializedDoc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocumentById(@PathVariable(name = "id") int id) {
        try {
            return new ResponseEntity<>(documentService.getDocumentById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getAllDocumentByUserId(@PathVariable(name = "userId") int userId) {
        try {
            return new ResponseEntity<>(documentService.getAllDocumentsByUserId(userId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/allDocs/")
    public ResponseEntity<?> getAllDocuments() {
        try {
            return new ResponseEntity<>(documentService.getAllDocuments(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocById(@PathVariable(name = "id") int id) {
        try {
            documentService.removeDocumentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}