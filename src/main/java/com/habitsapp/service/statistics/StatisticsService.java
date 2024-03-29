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

    /**
     * Retrieves statistics for goals grouped by category.
     *
     * @return A list of goalStatistics representing the count of goals in each category.
     */
    public List<GoalStatistics> getGoalStatistics() {
        return goalRepository.countGoalsByCategory();
    }
}
