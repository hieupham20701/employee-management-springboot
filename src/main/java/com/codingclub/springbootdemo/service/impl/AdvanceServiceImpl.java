package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.convert.ConvertEmployeeDTO;
import com.codingclub.springbootdemo.dto.AdvanceDTO;
import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.entity.Advance;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.repository.AdvanceRepository;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.service.AdvanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvanceServiceImpl implements AdvanceService {

    @Autowired
    private AdvanceRepository advanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConvertEmployeeDTO convertEmployeeDTO;
    @Override
    public List<AdvanceDTO> getListAdvanceByEmployeeId(Integer employee_id) {
        List<Advance> advances = advanceRepository.findByEmployee_Id(employee_id);
        List<AdvanceDTO> advanceDTOS = new ArrayList<>();
        for(Advance advance : advances){
            AdvanceDTO advanceDTO = modelMapper.map(advance, AdvanceDTO.class);
            Employee employee = employeeRepository.getById(employee_id);

            EmployeeDTO employeeDTO= convertEmployeeDTO.convertToDTO(employee);
            advanceDTO.setEmployeeDTO(employeeDTO);
            advanceDTOS.add(advanceDTO);
        }
        return advanceDTOS;
    }

    @Override
    public AdvanceDTO addAdvance(AdvanceDTO advanceDTO) {
        Advance advance = modelMapper.map(advanceDTO,Advance.class);
        System.out.println(advance.toString());
        Employee employee = employeeRepository.findById(advanceDTO.getEmployeeDTO().getId()).get();
        advance.setEmployee(employee);
        AdvanceDTO advanceDTORes = modelMapper.map(advanceRepository.save(advance), AdvanceDTO.class);
        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        advanceDTORes.setEmployeeDTO(employeeDTO);
        return advanceDTORes;
    }

    @Override
    public void deleteAdvance(Integer id) {
        advanceRepository.delete(advanceRepository.getById(id));
    }
}
