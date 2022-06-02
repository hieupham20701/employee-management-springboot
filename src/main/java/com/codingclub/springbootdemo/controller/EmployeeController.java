package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.convert.ConvertEmployeeDTO;
import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.service.EmployeeService;
import com.codingclub.springbootdemo.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConvertEmployeeDTO convertEmployeeDTO;
    @Autowired
    private ImageService imageService;
    @GetMapping
    public List<EmployeeDTO> getListEmployee(){

        List<Employee> employees = employeeService.getListEmployee();
        return  mapEmployeeDTO(employees);
    }
    @PostMapping(value = "/new",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws IOException {
        try {
            EmployeeDTO employeeDTO1 = convertEmployeeDTO.convertToDTO(employeeService.saveEmployee(
                   modelMapper.map(employeeDTO, Employee.class)));
            return ResponseEntity.ok().body(employeeDTO1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id){
        try {
            EmployeeDTO employeeDTO = convertEmployeeDTO.convertToDTO(employeeService.getEmployeeById(Integer.parseInt(id)));

            return ResponseEntity.ok().body(employeeDTO);


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }
    @PutMapping(value = "/{id}",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable String id) throws IOException {
        try {

            EmployeeDTO employeeDTO1 = convertEmployeeDTO.convertToDTO(employeeService.updateEmployee(modelMapper.map(employeeDTO, Employee.class), Integer.parseInt(id)));
            return ResponseEntity.ok().body(employeeDTO1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
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
    @DeleteMapping
    public ResponseEntity<ResponeMessage> deleteMultiEmployee(@RequestBody int[]id){
        try {
            employeeService.deleteEmployees(id);
            return ResponseEntity.ok().body(new ResponeMessage("Delete Success!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }
    @GetMapping(value = "/teams")
    public  List<EmployeeDTO> getEmployeesByTeamId(@RequestParam("team_id")String teamId ){

        List<Employee> employees = employeeService.getListEmployeeByTeamId(Integer.parseInt(teamId));

        return mapEmployeeDTO(employees);
    }
    private List<EmployeeDTO> mapEmployeeDTO(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            EmployeeDTO employeeDTO = convertEmployeeDTO.convertToDTO(employee);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Map<String, Object>> getAllEmployeePagination(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size ){
        Map<String, Object> result = employeeService.getAllEmployeePage(page, size);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/pages/search")
    public ResponseEntity<Map<String,Object>> getEmployeeByName(@RequestParam(defaultValue = "")String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10")int size){
        Map<String,Object> result = employeeService.getEmployeeByNameSearch(page,size,name);
        return ResponseEntity.ok().body(result);
    }
}
