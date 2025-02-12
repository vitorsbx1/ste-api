package com.vitorsb.itau.ste_api.controller;

import com.vitorsb.itau.ste_api.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public ResponseEntity<?> calculateStatistic(){
        return ResponseEntity.ok().body(statisticService.calculateStatistic());
    }
}
