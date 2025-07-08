package ua.dark.catparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.dark.catparser.service.RawReader;

@RestController
public class DataController {

    private final RawReader rawReader;

    @Autowired
    public DataController(RawReader rawReader) {
        this.rawReader = rawReader;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> loadData(@RequestParam("table") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }
        rawReader.processFile(file);
        return ResponseEntity.ok().body("Success");
    }
}
