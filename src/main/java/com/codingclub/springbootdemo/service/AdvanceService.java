package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.dto.AdvanceDTO;

import java.util.List;

public interface AdvanceService {

    List<AdvanceDTO> getListAdvanceByEmployeeId(Integer employee_id);
    AdvanceDTO addAdvance(AdvanceDTO advanceDTO);
    void deleteAdvance(Integer id);
}
