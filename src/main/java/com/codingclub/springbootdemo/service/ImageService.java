package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.entity.Image;

public interface ImageService {

    public Image saveImage(Image image);
    public Image getImageById(int id);

    public Image getImageByEmployeeId(int id);
}
