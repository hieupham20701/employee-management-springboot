package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Employee;
import com.codingclub.springbootdemo.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
}
