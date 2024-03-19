package com.habitsapp.service;

import com.habitsapp.model.Category;
import com.habitsapp.model.Goal;
import com.habitsapp.repository.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

//    private Goal goal;
//
//    @BeforeEach
//    public void setUpGoal(){
//        goal = new Goal();
//        goal.setId(1L);
//        goal.setName("Learn programming");
//        goal.setCategory(Category.EDUCATION);
//        goal.setStartDate(LocalDate.now());
//        goal.setEndDate(LocalDate.now().plusMonths(1));
//    }

    @Test
    void findAllGoals() {

        //GIVEN
        Goal goal1 = new Goal();
        Goal goal2 = new Goal();

        //WHEN
        when(goalRepository.findAll()).thenReturn(Arrays.asList(goal1, goal2));
        List<Goal> goals = goalService.findAllGoals();

        //THEN
        assertNotNull(goals);
        assertEquals(2, goals.size());
    }

    @Test
    void findGoalById() {

        //GIVEN
        Goal goal = new Goal();
        goal.setId(1L);

        //WHEN
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        Optional<Goal> foundGoal = goalService.findGoalById(1L);

        //THEN
        assertTrue(foundGoal.isPresent());
        assertEquals(1L, foundGoal.get().getId());
    }

    @Test
    void createGoal() {

        //GIVEN
        Goal goal = new Goal();
        goal.setName("CEL");

        //WHEN
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);
        Goal result = goalService.createGoal(goal);

        //THEN
        assertNotNull(result);
        assertEquals("CEL",result.getName());
    }

    @Test
    void deleteGoal() {

        //GIVEN
        Goal goal = new Goal();
        goal.setId(1L);

        //WHEN
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        doNothing().when(goalRepository).deleteById(1L);
        boolean isDeleted = goalService.deleteGoal(1L);

        //THEN
        assertTrue(isDeleted);
        verify(goalRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateGoal() {

        //GIVEN
        Goal goal = new Goal();
        goal.setId(1L);
        goal.setName("ZAROBIĆ PIEWSZY MILION");

        Goal updateGoal = new Goal();
        updateGoal.setName("PRZEZYC DO NASTEPNEJ WYPŁATY");
        updateGoal.setCategory(Category.FINANCE);

        //WHEN
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(goalRepository.save(any(Goal.class))).thenAnswer(c -> c.getArgument(0));
        Goal newGoal = goalService.updateGoal(1L, updateGoal);

        //THEN
        assertNotNull(newGoal);
        assertEquals("PRZEZYC DO NASTEPNEJ WYPŁATY", newGoal.getName());
        assertEquals(Category.FINANCE, newGoal.getCategory());
    }
}