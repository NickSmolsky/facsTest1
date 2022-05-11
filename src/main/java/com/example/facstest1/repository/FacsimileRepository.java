package com.example.facstest1.repository;

import com.example.facstest1.model.DocumentType;
import com.example.facstest1.model.Facsimile;
import com.example.facstest1.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacsimileRepository extends JpaRepository<Facsimile, Long> {
    Optional<Facsimile> findFacsimileByDocumentTypeAndStatus(DocumentType documentType, Status status);

}
