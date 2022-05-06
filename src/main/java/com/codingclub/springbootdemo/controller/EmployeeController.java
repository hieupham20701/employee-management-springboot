package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.dto.ImageDTO;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.codingclub.springbootdemo.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageService imageService;
    @GetMapping("/list")
    public List<EmployeeDTO> getListEmployee(){
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();
        List<Employee> employees = employeeService.getListEmployee();
        for(Employee employee : employees){
            EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
            Image image = imageService.getImageByEmployeeId(employee.getId());
            ImageDTO imageDTO = new ImageDTO();
            if(image != null){
                imageDTO =  modelMapper.map(image, ImageDTO.class);
                String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                        .path(imageDTO.getId() + "").toUriString();
                imageDTO.setUrl(url);
            }else{
                imageDTO = null;
            }

            employeeDTO.setImageDTO(imageDTO);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @PostMapping(value = "/new",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public EmployeeDTO saveEmployee(@RequestPart("employee") String employee, @RequestPart("file")MultipartFile file) throws IOException {
        Employee employeeJson = employeeService.converEmployeeJson(employee);
        EmployeeDTO employeeDTO = modelMapper.map(employeeService.saveEmployee(employeeJson),EmployeeDTO.class);
        Image image = new Image();
        image.setEmployee(employeeService.getEmployeeById(employeeJson.getId()));
        image.setFile(file.getBytes());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        image.setFileName(fileName);
        image.setFileType(file.getContentType());
        ImageDTO imageDTO =  modelMapper.map(imageService.saveImage(image), ImageDTO.class);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                .path(image.getId() + "").toUriString();
        imageDTO.setUrl(url);
        employeeDTO.setImageDTO(imageDTO);
        return employeeDTO;
    }

}
