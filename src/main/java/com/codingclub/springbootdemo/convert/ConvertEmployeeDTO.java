package com.codingclub.springbootdemo.convert;

import com.codingclub.springbootdemo.dto.EmployeeDTO;
import com.codingclub.springbootdemo.dto.ImageDTO;
import com.codingclub.springbootdemo.dto.TeamDTO;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Image;
import com.codingclub.springbootdemo.entity.Team;
import com.codingclub.springbootdemo.repository.EmployeeRepository;
import com.codingclub.springbootdemo.repository.ImageRepository;
import com.codingclub.springbootdemo.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ConvertEmployeeDTO {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ImageRepository imageRepository;
    public EmployeeDTO convertToDTO(Employee employee){
        Team team = teamRepository.getById(employee.getTeam().getId());
        TeamDTO teamDTO = modelMapper.map(team,TeamDTO.class);
        Image image = imageRepository.findByEmployee_Id(employee.getId());
        ImageDTO imageDTO;
        if(image != null){
            imageDTO = modelMapper.map(image,ImageDTO.class);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
                    .path(imageDTO.getId() + "").toUriString();
            imageDTO.setUrl(url);
        }else
            imageDTO = null;

        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        employeeDTO.setImageDTO(imageDTO);
        employeeDTO.setTeamDTO(teamDTO);
        return employeeDTO;
    }
}
