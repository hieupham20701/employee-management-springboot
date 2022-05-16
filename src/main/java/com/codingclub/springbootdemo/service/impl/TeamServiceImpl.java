package com.codingclub.springbootdemo.service.impl;

import com.codingclub.springbootdemo.dto.TeamDTO;
import com.codingclub.springbootdemo.entity.Team;
import com.codingclub.springbootdemo.repository.TeamRepository;
import com.codingclub.springbootdemo.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Team saveTeam(TeamDTO teamDTO) {
        Team team = modelMapper.map(teamDTO, Team.class );


        return teamRepository.save(team);
    }

    @Override
    public List<Team> getListTeam() {
        return teamRepository.findAll();
    }

    @Override
    public void deleteTeam(int teamId) {
        teamRepository.delete(teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team id " + teamId +" is not found")));
    }

}
