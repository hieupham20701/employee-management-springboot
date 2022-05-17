package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.dto.WorkingDTO;
import com.codingclub.springbootdemo.service.WorkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/workings")
public class WorkingController {

    @Autowired
    private WorkingService workingService;
    @GetMapping(value ="/{employee_id}")
    public List<WorkingDTO> getListWorkingByEmployeeId(@PathVariable("employee_id") String employee_id){
        return workingService.getListWorkingByEmployeeId(Integer.parseInt(employee_id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?>  addWorking(@RequestBody WorkingDTO workingDTO){
        try {
            return ResponseEntity.ok().body(workingService.addWorking(workingDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteWorking(@PathVariable String id ){
        try {
            workingService.deleteWorking(Integer.parseInt(id));
            return ResponseEntity.ok().body(new ResponeMessage("Delete Sucess!"));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }
}
