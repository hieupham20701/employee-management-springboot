package com.codingclub.springbootdemo.controller;


import com.codingclub.springbootdemo.dto.ImageDTO;
import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.codingclub.springbootdemo.service.ImageService;
import com.codingclub.springbootdemo.validation.ValidFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/images")
@Validated
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") int id){
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+image.getFileName()+"\"").body(image.getFile());
    }

    @PostMapping(value = "/new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> saveImage(@RequestParam("employee_id") String employee_id,@Valid @ValidFile @RequestParam("file") MultipartFile file ) throws IOException {

        try {
            Image image = imageService.saveImage(employee_id,file);
            ImageDTO imageDTO =  modelMapper.map(image, ImageDTO.class);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                    .path(image.getId() + "").toUriString();
            imageDTO.setUrl(url);
            return ResponseEntity.ok().body(imageDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }

    @PutMapping (value = "image/{image_id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateImage(@PathVariable String image_id,@Valid @ValidFile @RequestParam("file") MultipartFile file){
        try {
            Image image = new Image();
            image.setFile(file.getBytes());
            System.out.println(file.getContentType());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            image.setFileName(fileName);
            image.setFileType(file.getContentType());
            ImageDTO imageDTO =  modelMapper.map(imageService.updateImage(Integer.parseInt(image_id),image), ImageDTO.class);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                    .path(imageDTO.getId() + "").toUriString();
            imageDTO.setUrl(url);
            return ResponseEntity.ok().body(imageDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));

        }
    }
}
