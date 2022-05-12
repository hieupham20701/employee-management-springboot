package com.codingclub.springbootdemo.controller;

import com.codingclub.springbootdemo.dto.ResponeMessage;
import com.codingclub.springbootdemo.dto.TeamDTO;
import com.codingclub.springbootdemo.entity.Team;
import com.codingclub.springbootdemo.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping(value = "/new")
    public TeamDTO saveTeam(@RequestBody TeamDTO team){
        return modelMapper.map(teamService.saveTeam(team),TeamDTO.class);
    }

    @DeleteMapping(value = "/{teamId}")
    public ResponseEntity<ResponeMessage> deleteTeam(@PathVariable String teamId){
        try {
            teamService.deleteTeam(Integer.parseInt(teamId));
            return ResponseEntity.ok().body( new ResponeMessage("Delete Success!"));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage(e.getMessage()));
        }

    }

}
