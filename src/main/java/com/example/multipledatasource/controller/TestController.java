package com.example.multipledatasource.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multipledatasource.entity.postgresql.Bug;
import com.example.multipledatasource.entity.postgresql.Season;
import com.example.multipledatasource.entity.sqlserver.Flower;
import com.example.multipledatasource.service.BugService;
import com.example.multipledatasource.service.FlowerService;
import com.example.multipledatasource.service.SeasonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TestController {

    private BugService bugService;
    private FlowerService flowerService;
    private SeasonService seasonService;

    @GetMapping("/bugs")
    public List<Bug> getBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/flowers")
    public List<Flower> getFlowers() {
        return flowerService.getAllFlowers();
    }

    @GetMapping("/seasons")
    public List<Season> getSeasons() {
        return seasonService.getAllSeasons();
    }

}
