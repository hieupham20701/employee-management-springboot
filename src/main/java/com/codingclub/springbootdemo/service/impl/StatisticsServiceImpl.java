package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.convert.ConvertEmployeeDTO;
import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.dto.StatisticDTO;
import com.codingclub.springbootdemo.entity.Advance;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Working;
import com.codingclub.springbootdemo.repository.AdvanceRepository;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.repository.WorkingRepository;
import com.codingclub.springbootdemo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkingRepository workingRepository;

    @Autowired
    private AdvanceRepository advanceRepository;

    @Autowired
    private ConvertEmployeeDTO convertEmployeeDTO;
    @Override
    public StatisticDTO getStatisticOfEmployee(Integer employee_id, Integer month) {
//        System.out.println(LocalDate.now().getMonth());
        Employee employee = employeeRepository.getById(employee_id);
        EmployeeDTO employeeDTO = convertEmployeeDTO.convertToDTO(employee);
        List<Working> workingList = workingRepository.findAllByEmployee_Id(employee_id);
        List<Advance> advanceList = advanceRepository.findByEmployee_Id(employee_id);
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setEmployeeDTO(employeeDTO);
        Double totalGet = 0.0;
        Double totalAdvance = 0.0;
        Integer numOfWorkingDays = 0;
        for (Working working : workingList){
            if(working.getDate().getMonth()+1 == month) {
                totalGet += working.getHour() * working.getEmployee().getMoneyPerHour();
                numOfWorkingDays++;
            }
        }
        for (Advance advance : advanceList){
            if(advance.getDate().getMonth()+1 == month){
                totalAdvance += advance.getMoney();
            }
        }
        statisticDTO.setNumOfWorkingDays(numOfWorkingDays);
        statisticDTO.setTotalGet(totalGet);
        statisticDTO.setTotalAdvance(totalAdvance);
        statisticDTO.setSummary(totalGet - totalAdvance);
        return statisticDTO;
    }
}
