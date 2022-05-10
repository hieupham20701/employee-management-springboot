package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.repository.ImageRepository;
import com.codingclub.springbootdemo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(Image image) {
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
            oldImage.setEmployee(image.getEmployee());
        }
        return imageRepository.save(oldImage);
    }
}
