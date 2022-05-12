package com.codingclub.springbootdemo.service;

import com.codingclub.springbootdemo.dto.TeamDTO;
import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(TeamDTO team);

    List<Team> getListTeam();
    void deleteTeam(int teamId);


}
