package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    public Image saveImage(String employeeId, MultipartFile file) throws IOException;
    public Image getImageById(int id);

    public Image getImageByEmployeeId(int id);

    public Image updateImage(int id, Image image);
}
