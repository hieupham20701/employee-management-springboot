package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Working;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkingRepository extends JpaRepository<Working, Integer> {
    List<Working> findAllByEmployee_Id(Integer id);
}