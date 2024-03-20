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

    private Goal existingGoal;
    private Goal updatedGoalDetails;

    @BeforeEach
    void setUp() {
        existingGoal = new Goal();
        existingGoal.setId(1L);
        existingGoal.setName("Original Goal");
        existingGoal.setCategory(Category.DIET);
        existingGoal.setStartDate(LocalDate.of(2023, 1, 1));
        existingGoal.setEndDate(LocalDate.of(2023, 12, 31));

        updatedGoalDetails = new Goal();
        updatedGoalDetails.setName("Updated Goal");
        updatedGoalDetails.setCategory(Category.HEALTH);
        updatedGoalDetails.setStartDate(LocalDate.of(2023, 2, 1));
        updatedGoalDetails.setEndDate(LocalDate.of(2023, 11, 30));

        Mockito.when(goalRepository.findById(existingGoal.getId())).thenReturn(Optional.of(existingGoal));
        Mockito.when(goalRepository.save(any(Goal.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }


    @Test
    void updateGoal_ShouldCorrectlyUpdateGoal() {
        Goal updatedGoal = goalService.updateGoal(existingGoal.getId(), updatedGoalDetails);

        assertNotNull(updatedGoal);
        assertEquals(updatedGoalDetails.getName(), updatedGoal.getName());
        assertEquals(updatedGoalDetails.getCategory(), updatedGoal.getCategory());
        assertEquals(updatedGoalDetails.getStartDate(), updatedGoal.getStartDate());
        assertEquals(updatedGoalDetails.getEndDate(), updatedGoal.getEndDate());

        Mockito.verify(goalRepository).save(any(Goal.class));
    }

//    @Test
//    void findAllGoals() {
//
//    }
//
//    @Test
//    void findGoalById() {
//
//    }
//
//    @Test
//    void createGoal() {
//
//    }
//
//    @Test
//    void deleteGoal() {
//
//    }
}