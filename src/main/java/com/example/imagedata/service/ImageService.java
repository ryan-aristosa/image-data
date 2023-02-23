package com.example.imagedata.service;

import com.example.imagedata.dto.ImageDTO;
import com.example.imagedata.exception.ImageAlreadyExistingException;
import com.example.imagedata.exception.RecordNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String saveImage(MultipartFile multipartFile) throws ImageAlreadyExistingException, IOException;

    ImageDTO findImageByName(String name) throws RecordNotFoundException;

}
