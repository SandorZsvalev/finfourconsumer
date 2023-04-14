package org.finfourconsumer.project.service;

import org.apache.camel.Exchange;
import org.finfourconsumer.project.model.Document;

import java.util.List;

public interface DocumentService {

    Document registerDocument(Exchange exchange);
//    Document registerFromApiDocument(Exchange exchange);

    List<Document> getAllDocumentsByUserId(int userId) throws RuntimeException;

    Document registerDocument(Document document);

    Document getDocumentById(int id);

    List<Document> getAllDocuments();

    void removeDocumentById(int id);
}
