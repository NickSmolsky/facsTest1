package com.example.facstest1.service;

import com.example.facstest1.model.DocumentType;
import com.example.facstest1.model.Facsimile;
import com.example.facstest1.model.Status;
import com.example.facstest1.repository.FacsimileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FacsimileService {

    private final FacsimileRepository facsimileRepository;

    @Autowired
    public FacsimileService(FacsimileRepository facsimileRepository) {
        this.facsimileRepository = facsimileRepository;
    }

    @Transactional
    public void save(Facsimile facsimile) {
        facsimileRepository.save(facsimile);
    }
    @Transactional
    public Optional<List<Facsimile>> getAllFacsimile() {
        List<Facsimile> facsimileList = facsimileRepository.findAll();
        if (facsimileList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(facsimileList);
    }

    @Transactional
    public Optional<Facsimile> getFacsimileByDocumentTypeAndStatus(DocumentType documentType, Status status) {
        return facsimileRepository.findFacsimileByDocumentTypeAndStatus(documentType, status);
    }
    public void changeStatusFacsimile(DocumentType documentType, Status status) {
        Optional<Facsimile> optionalFacsimile = getFacsimileByDocumentTypeAndStatus(documentType, status);
        if (optionalFacsimile.isPresent()) {
            Facsimile facsimile = optionalFacsimile.get();
            facsimile.setStatus(Status.ARCHIVE);
            save(facsimile);
        }
    }


    //    @Transactional
//    public void uploadFacsimile(MultipartFile uploadFile) throws IOException {
//        Facsimile facsimile = new Facsimile();
//        facsimile.setName(StringUtils.cleanPath(Objects.requireNonNull(uploadFile.getOriginalFilename())));
//        facsimile.setImage(uploadFile.getBytes());
//        facsimileRepository.save(facsimile);
//    }

    //    @Transactional
//    public Optional<Facsimile> getFacsimile(Long id) {
//        return facsimileRepository.findById(id);
//    }




}
