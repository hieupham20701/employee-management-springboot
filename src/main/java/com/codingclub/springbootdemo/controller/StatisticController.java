package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.dto.StatisticDTO;
import com.codingclub.springbootdemo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/statistic")
public class StatisticController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<?> getStatistic(@RequestParam("employee_id")String employee_id, @RequestParam("month") String month ){
        try {
            StatisticDTO statisticDTO =  statisticsService.getStatisticOfEmployee(Integer.parseInt(employee_id), Integer.parseInt(month));
            return ResponseEntity.ok().body(statisticDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }
    }
}
