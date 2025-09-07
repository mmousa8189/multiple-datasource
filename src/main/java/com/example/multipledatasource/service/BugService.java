package com.example.multipledatasource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.multipledatasource.repository.postgresql.BugRepository;
import lombok.AllArgsConstructor;
import com.example.multipledatasource.aspect.DataSource;
import com.example.multipledatasource.entity.postgresql.Bug;

@Service
@AllArgsConstructor
public class BugService {

    private BugRepository bugRepository;

    @DataSource("postgresql")
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }
}