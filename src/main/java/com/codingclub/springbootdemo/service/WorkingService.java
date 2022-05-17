package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.dto.WorkingDTO;
import com.codingclub.springbootdemo.entity.Working;

import java.util.List;

public interface WorkingService {

    List<WorkingDTO> getListWorkingByEmployeeId(Integer employeeId);
    WorkingDTO addWorking(WorkingDTO workingDTO);
    void deleteWorking(Integer workingId);

}
