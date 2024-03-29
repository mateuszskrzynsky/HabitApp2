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

    /**
     * Handles the request to display goal statistics.
     * This method is mapped to the GET request of '/statistics' URL.
     * It fetches a list of goal statistics using the statisticsService,
     * then adds this list to the model under the attribute name "statistics".
     *
     * @param model The Model object that will be used to add attributes to the model.
     *              This is automatically provided by Spring MVC.
     * @return This method returns "statistics",
     *         which means that the request will be forwarded to the "statistics.html" view.
     */
    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<GoalStatistics> statistics = statisticsService.getGoalStatistics();
        model.addAttribute("statistics", statistics);
        return "statistics";
    }

}
