package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.dto.ImageDTO;
import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.codingclub.springbootdemo.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/images")
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

//    @PostMapping(value = "/new", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
//    public ImageDTO saveImage(@RequestParam("employee_id") String employee_id, @RequestParam("file") MultipartFile file ) throws IOException {
//        Image image = new Image();
//        image.setEmployee(employeeService.getEmployeeById(Integer.parseInt(employee_id)));
//        image.setFile(file.getBytes());
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        System.out.println(fileName);
//        image.setFileName(fileName);
//        image.setFileType(file.getContentType());
//        ImageDTO imageDTO =  modelMapper.map(imageService.saveImage(image), ImageDTO.class);
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
//                .path(image.getId() + "").toUriString();
//        imageDTO.setUrl(url);
//        return imageDTO;
//
//    }
}
