package org.finfourconsumer.project.repository;

import org.finfourconsumer.project.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    Optional<List<Document>> findAllByUserId(int userId);

}
