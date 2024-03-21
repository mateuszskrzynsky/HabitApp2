package com.habitsapp.service;

import com.habitsapp.model.Category;
import com.habitsapp.model.Goal;
import com.habitsapp.repository.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoalServiceTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalService goalService;

    private Goal goal;

    @BeforeEach
    void setUp() {
        goal = new Goal();
        goal.setId(1L);
        goal.setName("Learn Spring Boot");
        goal.setCategory(Category.DIET);
        goal.setStartDate(LocalDate.of(2023, 1, 1));
        goal.setEndDate(LocalDate.of(2023, 12, 31));
    }

    @Test
    void findAllGoals_ShouldReturnAllGoals() {
        when(goalRepository.findAll()).thenReturn(Arrays.asList(goal));

        List<Goal> goals = goalService.findAllGoals();

        assertNotNull(goals);
        assertFalse(goals.isEmpty());
        assertEquals(1, goals.size());
        assertEquals(goal.getId(), goals.get(0).getId());
    }

    @Test
    void findGoalById_WhenGoalExists_ShouldReturnGoal() {
        when(goalRepository.findById(eq(1L))).thenReturn(Optional.of(goal));

        Optional<Goal> foundGoal = goalService.findGoalById(1L);

        assertTrue(foundGoal.isPresent());
        assertEquals(goal.getId(), foundGoal.get().getId());
    }

    @Test
    void createGoal_ShouldSaveAndReturnGoal() {
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);

        Goal savedGoal = goalService.createGoal(goal);

        assertNotNull(savedGoal);
        assertEquals(goal.getName(), savedGoal.getName());
    }

    @Test
    void deleteGoal_WhenGoalExists_ShouldDeleteGoal() {
        when(goalRepository.findById(eq(1L))).thenReturn(Optional.of(goal));
        doNothing().when(goalRepository).deleteById(eq(1L));

        boolean isDeleted = goalService.deleteGoal(1L);

        assertTrue(isDeleted);
        verify(goalRepository, times(1)).deleteById(eq(1L));
    }

    @Test
    void updateGoal_ShouldCorrectlyUpdateGoalDetails() {
        Goal updatedDetails = new Goal();
        updatedDetails.setName("Updated Name");
        updatedDetails.setCategory(Category.HEALTH);
        updatedDetails.setStartDate(LocalDate.of(2023, 2, 1));
        updatedDetails.setEndDate(LocalDate.of(2023, 11, 30));

        when(goalRepository.findById(eq(1L))).thenReturn(Optional.of(goal));
        when(goalRepository.save(any(Goal.class))).then(invocation -> invocation.getArgument(0));

        Goal updatedGoal = goalService.updateGoal(1L, updatedDetails);

        assertNotNull(updatedGoal);
        assertEquals(updatedDetails.getName(), updatedGoal.getName());
        assertEquals(updatedDetails.getCategory(), updatedGoal.getCategory());
        assertEquals(updatedDetails.getStartDate(), updatedGoal.getStartDate());
        assertEquals(updatedDetails.getEndDate(), updatedGoal.getEndDate());

        verify(goalRepository, times(1)).save(goal);
    }
}