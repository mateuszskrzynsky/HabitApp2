package com.habitsapp.controller.statistics;

import com.habitsapp.dto.GoalStatistics;
import com.habitsapp.service.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<GoalStatistics> statistics = statisticsService.getGoalStatistics();
        model.addAttribute("statistics", statistics);
        return "statistics";
    }

}
