package com.example.multipledatasource.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.multipledatasource.entity.postgresql.Season;
import com.example.multipledatasource.repository.postgresql.SeasonRepository;
import lombok.AllArgsConstructor;
import com.example.multipledatasource.aspect.DataSource;

@Service
@AllArgsConstructor
public class SeasonService {

    private SeasonRepository seasonRepository;

    @DataSource("postgresql")
    public List<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }
}
