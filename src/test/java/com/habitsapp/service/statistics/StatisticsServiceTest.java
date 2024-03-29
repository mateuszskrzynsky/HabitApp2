package com.habitsapp.service.statistics;

import com.habitsapp.dto.GoalStatistics;
import com.habitsapp.model.Category;
import com.habitsapp.repository.GoalRepository;
import com.habitsapp.service.GoalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    void getGoalStatistics_ShouldReturnStatistics() {

        List<GoalStatistics> expectedStatistics = Arrays.asList(
                new GoalStatistics(Category.DIET, 10),
                new GoalStatistics(Category.FINANCE, 5)
        );


        when(goalRepository.countGoalsByCategory()).thenReturn(expectedStatistics);


        List<GoalStatistics> actualStatistics = statisticsService.getGoalStatistics();

        assertEquals(expectedStatistics, actualStatistics, "The returned statistics should match the expected ones.");
        verify(goalRepository, times(1)).countGoalsByCategory();
    }
}