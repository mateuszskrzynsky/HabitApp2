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

//    @Test
//    void findAllGoals() {
//
//        //GIVEN
//        Goal goal1 = new Goal();
//        Goal goal2 = new Goal();
//
//        //WHEN
//        when(goalRepository.findAll()).thenReturn(Arrays.asList(goal1, goal2));
//        List<Goal> goals = goalService.findAllGoals();
//
//        //THEN
//        assertNotNull(goals);
//        assertEquals(2, goals.size());
//    }
//
//    @Test
//    void findGoalById() {
//
//        //GIVEN
//        Goal goal = new Goal();
//        goal.setId(1L);
//
//        //WHEN
//        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
//        Optional<Goal> foundGoal = goalService.findGoalById(1L);
//
//        //THEN
//        assertTrue(foundGoal.isPresent());
//        assertEquals(1L, foundGoal.get().getId());
//    }
//
//    @Test
//    void createGoal() {
//
//        //GIVEN
//        Goal goal = new Goal();
//        goal.setName("CEL");
//
//        //WHEN
//        when(goalRepository.save(any(Goal.class))).thenReturn(goal);
//        Goal result = goalService.createGoal(goal);
//
//        //THEN
//        assertNotNull(result);
//        assertEquals("CEL",result.getName());
//    }
//
//    @Test
//    void deleteGoal() {
//
//        //GIVEN
//        Goal goal = new Goal();
//        goal.setId(1L);
//
//        //WHEN
//        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
//        doNothing().when(goalRepository).deleteById(1L);
//        boolean isDeleted = goalService.deleteGoal(1L);
//
//        //THEN
//        assertTrue(isDeleted);
//        verify(goalRepository, times(1)).deleteById(1L);
//    }

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
}