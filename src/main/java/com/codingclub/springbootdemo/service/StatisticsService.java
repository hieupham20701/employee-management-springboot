package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.dto.StatisticDTO;

public interface StatisticsService {

    StatisticDTO getStatisticOfEmployee(Integer employee_id, Integer month);
}
