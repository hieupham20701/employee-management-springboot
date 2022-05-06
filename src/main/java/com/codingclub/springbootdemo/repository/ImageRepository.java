package com.codingclub.springbootdemo.repository;

import com.codingclub.springbootdemo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    public Image findByEmployee_Id(int id);
}
