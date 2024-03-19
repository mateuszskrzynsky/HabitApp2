package com.habitsapp.controller;

import com.habitsapp.model.Goal;
import com.habitsapp.model.Habit;
import com.habitsapp.service.GoalService;
import com.habitsapp.service.HabitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitControllerTest {

    @Mock
    private HabitService habitService;

    @InjectMocks
    private HabitController habitController;

    @Test
    void getAllHabits() {
    }

    @Test
    void getHabit() {
    }

    @Test
    void createHabit() {

        //GIVEN
        Habit habit = new Habit();
        habit.setName("NAWYK");

        //WHEN
        when(habitService.createHabit(any(Habit.class))).thenReturn(habit);
        Habit result = habitService.createHabit(habit);
        ResponseEntity<Habit> response= habitController.createHabit(habit);

        //THEN
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(result, response.getBody());
    }

    @Test
    void deleteHabit() {

        //GIVEN
        Long id= 1L;

        //WHEN
        ResponseEntity<Habit> response = habitController.deleteHabit(id);

        //THEN
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(habitService).deleteHabit(id);
    }
}