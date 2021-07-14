package com.finalprojectaden.hospitalbooking.controller;

import com.finalprojectaden.hospitalbooking.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin
@RestController
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/files/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {

        String path = storageService.store(file);
        path = "http://127.0.0.1:8080/files/" + path;
        FileResponse fileResponse = new FileResponse();
        fileResponse.setName(path);

        return new ResponseEntity(fileResponse, HttpStatus.OK);
    }

    public class FileResponse {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
