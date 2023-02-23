package com.example.imagedata.service;

import com.example.imagedata.dto.ImageDTO;
import com.example.imagedata.exception.ImageAlreadyExistingException;
import com.example.imagedata.exception.RecordNotFoundException;
import com.example.imagedata.model.Image;
import com.example.imagedata.repository.ImageRepository;
import com.example.imagedata.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String saveImage(MultipartFile multipartFile) throws ImageAlreadyExistingException, IOException {
        String name = multipartFile.getOriginalFilename();
        Optional<Image> imageOptional = imageRepository.findByName(name);
        if (imageOptional.isPresent()) {
            throw new ImageAlreadyExistingException("Image already existing");
        }
        imageRepository.save(
                Image.builder()
                        .name(name)
                        .type(multipartFile.getContentType())
                        .image(ImageUtil.compressImage(multipartFile.getBytes()))
                        .build()
        );
        return "Image uploaded successfully: " + name;
    }

    @Override
    public ImageDTO findImageByName(String name) throws RecordNotFoundException {
        Optional<Image> imageOptional = imageRepository.findFirstByNameLike(
                name.replaceAll("%20", " "),
                Sort.by("id").descending()
        );
        if (imageOptional.isEmpty()) {
            throw new RecordNotFoundException("Record not found");
        }
        ImageDTO imageDTO = modelMapper.map(imageOptional.get(), ImageDTO.class);
        imageDTO.setImage(ImageUtil.decompressImage(imageDTO.getImage()));
        return imageDTO;
    }

}
