package org.finfourconsumer.project.service;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.finfourconsumer.project.model.Document;
import org.finfourconsumer.project.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public Document registerDocument(Exchange exchange) {
        //из ексченча делаем документ и его уже передаем в метод сэйв

        Document document = new Document();
        Message message = exchange.getIn();

        String originDocName = message.getHeader("CamelFileName", String.class);
        byte[] body = message.getBody(byte[].class);
        long docNumber = message.getHeader("docNumber", long.class);
        long docSum = message.getHeader("docSum", long.class);
        int userId = message.getHeader("userId", int.class);
        String comment = message.getHeader("comment", String.class);
        String direction = message.getHeader("direction", String.class);

        document.setDate(new Date(System.currentTimeMillis()));
        document.setOriginDocName(originDocName);
        document.setBody(body);
        document.setNumber(docNumber);
        document.setUserId(userId);
        document.setComment(comment);
        document.setDirection(direction);
        document.setSum(docSum);

        return documentRepository.save(document);
    }

    @Override
    public List<Document> getAllDocumentsByUserId(int userId) {
        return documentRepository.findAllByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Document registerDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document getDocumentById(int id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public void removeDocumentById(int id) {
        Document documentById = findDocumentById(id);
        documentRepository.delete(documentById);
    }

    private Document findDocumentById(int id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }

}
