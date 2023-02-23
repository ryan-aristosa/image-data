package com.example.imagedata.dto;

import lombok.Data;

@Data
public class ImageDTO {

    private Long id;
    private String name;
    private String type;
    private byte[] image;

}
