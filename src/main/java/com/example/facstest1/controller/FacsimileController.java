package com.example.facstest1.controller;

import com.example.facstest1.model.Facsimile;
import com.example.facstest1.model.Status;
import com.example.facstest1.service.FacsimileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("facsimiles")
public class FacsimileController {
    private FacsimileService facsimileService;

    @Autowired
    public FacsimileController(FacsimileService facsimileService) {
        this.facsimileService = facsimileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFacsimile(@RequestPart Facsimile facsimile,
                                                  @RequestPart MultipartFile uploadFile) {

        if (uploadFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Загружаемый файл не выбран или не содержит данных");
        }

        if (!(uploadFile.getContentType().equalsIgnoreCase("image/jpg")
                || uploadFile.getContentType().equalsIgnoreCase("image/jpeg")
                || uploadFile.getContentType().equalsIgnoreCase("image/png"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("Загружаемый файл: %s не соответствует требуемому расширению (jpg/png)", uploadFile.getOriginalFilename()));
        }

        try {
            facsimileService.changeStatusFacsimile(facsimile.getDocumentType(), Status.ACTIVE);
            facsimile.setImage(uploadFile.getBytes());
            facsimileService.save(facsimile);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("Файл: %s успешно загружен", uploadFile.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Не удалось загрузить файл: %s!", uploadFile.getOriginalFilename()));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllFacsimile() {

        Optional<List<Facsimile>> optionalFacsimiles = facsimileService.getAllFacsimile();

        if (optionalFacsimiles.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(optionalFacsimiles.get());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Список факсимиле не найден");
    }

    //    @GetMapping("{id}")
//    public ResponseEntity<Facsimile> getFacsimile(@PathVariable Long id) {
//        Optional<Facsimile> facsimileOptional = facsimileService.getFacsimile(id);
//        if (facsimileOptional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        Facsimile facsimile = facsimileOptional.get();
//        return ResponseEntity.status(HttpStatus.OK)
////                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + facsimile.getName() + "\"")
////                    .contentType(MediaType.valueOf(facsimile.getContentType()))
//                .body(facsimile);
//
//    }









}
