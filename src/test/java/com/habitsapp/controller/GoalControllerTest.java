package com.habitsapp.controller;

import com.habitsapp.model.Category;
import com.habitsapp.model.Goal;
import com.habitsapp.service.GoalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalControllerTest {

    @Mock
    private GoalService goalService;

    @InjectMocks
    private GoalController goalController;

    @Test
    void getAllGoals() {
        //GIVEN
        Goal goal1 = new Goal();
        Goal goal2 = new Goal();
        //WHEN
        when(goalService.findAllGoals()).thenReturn(List.of(goal1, goal2));
        List<Goal> goals = goalService.findAllGoals();
        ResponseEntity<List<Goal>> response = goalController.getAllGoals();
        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, goals.size());
    }

    @Test
    void getGoal() {
        //GIVEN
        Long id = 1L;
        Goal goal=new Goal();
        //WHEN
        when(goalService.findGoalById(id)).thenReturn(Optional.of(goal));
        ResponseEntity<Goal> response = goalController.getGoal(id);
        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(goal, response.getBody());
    }

    @Test
    void createGoal() {

        //GIVEN
        Goal goal = new Goal();
        goal.setName("CEL");

        //WHEN
        when(goalService.createGoal(any(Goal.class))).thenReturn(goal);
        Goal result = goalService.createGoal(goal);
        ResponseEntity<Goal> response= goalController.createGoal(goal);

        //THEN
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(result, response.getBody());

    }

    @Test
    void deleteGoal() {

        //GIVEN
        Long id= 1L;

        //WHEN
        ResponseEntity<Goal> response = goalController.deleteGoal(id);

        //THEN
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(goalService).deleteGoal(id);
    }

    @Test
    void updateGoal() {
        //GIVEN
        Goal goal = new Goal();
        goal.setId(1L);
        goal.setName("EAT MORE PROTEIN");
        goal.setCategory(Category.DIET);
        goal.setStartDate(LocalDate.now());
        goal.setEndDate(LocalDate.now().plusDays(30));

        Goal updatedGoal = new Goal();
        updatedGoal.setId(1L);
        updatedGoal.setName("EAT MORE PROTEIN AND LESS FAT");
        updatedGoal.setCategory(Category.DIET);
        updatedGoal.setStartDate(LocalDate.now());
        updatedGoal.setEndDate(LocalDate.now().plusDays(30));
        //WHEN
        when(goalService.findGoalById(goal.getId())).thenReturn(Optional.of(goal));
        when(goalService.createGoal(any())).thenAnswer(InvocationOnMock::getArguments);
        ResponseEntity<?> response= goalController.updateGoal(1L, goal);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedGoal, response.getBody());
    }
}