package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.dto.AdvanceDTO;
import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.service.AdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/advance")
@Validated
public class AdvanceController {

    @Autowired
    private AdvanceService advanceService;

    @GetMapping("/{employee_id}")
    public List<AdvanceDTO> getListAdvanceByEmployee(@PathVariable String employee_id){
        return advanceService.getListAdvanceByEmployeeId(Integer.parseInt(employee_id));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> addAdvance(@RequestBody AdvanceDTO advanceDTO){
        try {
            return ResponseEntity.ok().body(advanceService.addAdvance(advanceDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAdvance(@PathVariable String id){
        try {
            advanceService.deleteAdvance(Integer.parseInt(id));
            return ResponseEntity.ok().body(new ResponeMessage("Delete Sucess"));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }
}
