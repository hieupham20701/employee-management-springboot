package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.repository.ImageRepository;
import com.codingclub.springbootdemo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public Image saveImage(String employeeId, MultipartFile file) throws IOException {
        Image image = new Image();
        image.setEmployee(employeeRepository.findById(Integer.parseInt(employeeId)).get());
        image.setFile(file.getBytes());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        image.setFileName(fileName);
        image.setFileType(file.getContentType());
        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(int id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public Image getImageByEmployeeId(int id) {
        return imageRepository.findByEmployee_Id(id);
    }

    @Override
    public Image updateImage(int id, Image image) {
        Image oldImage = imageRepository.findById(id).get();

        if(image.getFile() != null){
            oldImage.setFileType(image.getFileType());
            oldImage.setFile(image.getFile());
            oldImage.setFileName(image.getFileName());
        }
        return imageRepository.save(oldImage);
    }
}
