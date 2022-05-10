package com.codingclub.springbootdemo.controller;
import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.dto.ImageDTO;
import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.codingclub.springbootdemo.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Employee, Image> result = convert(employee,file);
        Employee employeeJson = result.entrySet().iterator().next().getKey();
        Image image = result.entrySet().iterator().next().getValue();
        EmployeeDTO employeeDTO = modelMapper.map(employeeService.saveEmployee(
                employeeJson),EmployeeDTO.class);

        image.setEmployee(employeeService.getEmployeeById(employeeDTO.getId()));
        ImageDTO imageDTO =  modelMapper.map(imageService.saveImage(image), ImageDTO.class);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                .path(image.getId() + "").toUriString();
        imageDTO.setUrl(url);
        employeeDTO.setImageDTO(imageDTO);
        return employeeDTO;
    }

    @PutMapping(value = "/{id}",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE } )
    public EmployeeDTO updateEmployee(@RequestPart("employee") String employee, @RequestPart("file") MultipartFile file, @PathVariable String id) throws IOException {
        Map<Employee, Image> result = convert(employee, file);
        Employee employeeJson = result.entrySet().iterator().next().getKey();
        Image image = result.entrySet().iterator().next().getValue();
        EmployeeDTO employeeDTO = modelMapper.map(employeeService.updateEmployee(employeeJson, Integer.parseInt(id)), EmployeeDTO.class);
        image.setEmployee(employeeService.getEmployeeById(Integer.parseInt(id)));
        System.out.println(imageService.getImageByEmployeeId(employeeDTO.getId()).toString());
        ImageDTO imageDTO = modelMapper.map(imageService.updateImage(imageService.getImageByEmployeeId(Integer.parseInt(id)).getId(), image), ImageDTO.class);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                .path(imageDTO.getId() + "").toUriString();
        imageDTO.setUrl(url);
        employeeDTO.setImageDTO(imageDTO);
        return employeeDTO;
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponeMessage> deleteEmployee(@PathVariable String id){
        try {
            employeeService.deleteEmployee(Integer.parseInt(id));
            return ResponseEntity.ok().body(new ResponeMessage("Delete Success"));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete"));
        }
    }
    private Map<Employee,Image> convert(String employee, MultipartFile file) throws IOException {
        Employee employeeJson = employeeService.converEmployeeJson(employee);
        Image image = new Image();
        image.setFile(file.getBytes());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        image.setFileName(fileName);
        image.setFileType(file.getContentType());
        Map<Employee, Image> result = new HashMap<Employee, Image>();
        result.put(employeeJson, image);
        return result;
    }
}
