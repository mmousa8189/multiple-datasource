package com.example.multipledatasource.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.multipledatasource.entity.sqlserver.Flower;
import com.example.multipledatasource.repository.sqlserver.FlowerRepository;
import lombok.AllArgsConstructor;
import com.example.multipledatasource.aspect.DataSource;

@Service
@AllArgsConstructor
public class FlowerService {

    private FlowerRepository flowerRepository;

    @DataSource("sqlserver")
    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }
}