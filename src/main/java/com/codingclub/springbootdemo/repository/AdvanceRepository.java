package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Advance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdvanceRepository extends JpaRepository<Advance, Integer> {
    List<Advance> findByEmployee_Id(Integer id);

}