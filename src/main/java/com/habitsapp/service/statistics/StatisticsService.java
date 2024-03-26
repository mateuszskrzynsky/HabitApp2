package com.habitsapp.service.statistics;

import com.habitsapp.dto.GoalStatistics;
import com.habitsapp.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private final GoalRepository goalRepository;

    @Autowired
    public StatisticsService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<GoalStatistics> getGoalStatistics() {
        return goalRepository.countGoalsByCategory();
    }
}
