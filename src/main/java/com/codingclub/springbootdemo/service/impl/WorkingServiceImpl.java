package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.convert.ConvertEmployeeDTO;
import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.dto.WorkingDTO;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Working;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.repository.WorkingRepository;
import com.codingclub.springbootdemo.service.WorkingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkingServiceImpl implements WorkingService {

    @Autowired
    private WorkingRepository workingRepository;

    @Autowired
    private ConvertEmployeeDTO convertEmployeeDTO;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<WorkingDTO> getListWorkingByEmployeeId(Integer employeeId) {
        List<Working> workingList = workingRepository.findAllByEmployee_Id(employeeId);
        List<WorkingDTO> workingDTOS = new ArrayList<WorkingDTO>();
        for(Working working : workingList){
            WorkingDTO workingDTO = modelMapper.map(working,WorkingDTO.class);
            Employee employee = employeeRepository.getById(employeeId);
            EmployeeDTO employeeDTO = convertEmployeeDTO.convertToDTO(employee);
            workingDTO.setEmployeeDTO(employeeDTO);
            workingDTOS.add(workingDTO);
        }
        return workingDTOS;
    }
    @Override
    public WorkingDTO addWorking(WorkingDTO workingDTO) {
        Working working = modelMapper.map(workingDTO, Working.class);
        Employee employee = employeeRepository.findEmployeeById(workingDTO.getEmployeeDTO().getId());
        working.setEmployee(employee);
        EmployeeDTO employeeDTORes = convertEmployeeDTO.convertToDTO(employee);
        WorkingDTO workingDTORes = modelMapper.map(workingRepository.save(working), WorkingDTO.class);
        workingDTORes.setEmployeeDTO(employeeDTORes);

        return workingDTORes;
    }

    @Override
    public void deleteWorking(Integer workingId) {
        workingRepository.delete(workingRepository.findById(workingId).get());
    }
}
