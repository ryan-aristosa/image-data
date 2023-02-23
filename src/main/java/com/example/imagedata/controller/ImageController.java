package com.example.imagedata.controller;

import com.example.imagedata.exception.ImageAlreadyExistingException;
import com.example.imagedata.exception.RecordNotFoundException;
import com.example.imagedata.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    @Autowired
    private ImageService imageService;


    @PostMapping
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile multipartFile)
            throws ImageAlreadyExistingException, IOException {
        return new ResponseEntity<>(imageService.saveImage(multipartFile), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getImage(@PathVariable String name) throws RecordNotFoundException {
        return new ResponseEntity<>(imageService.findImageByName(name), HttpStatus.OK);
    }

}
