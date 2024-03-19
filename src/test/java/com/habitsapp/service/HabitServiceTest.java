package com.habitsapp.service;

import com.habitsapp.model.Goal;
import com.habitsapp.model.Habit;
import com.habitsapp.repository.GoalRepository;
import com.habitsapp.repository.HabitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {
    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;


    @Test
    void findAllHabits() {
        //GIVEN
        Habit habit1 = new Habit();
        Habit habit2 = new Habit();

        //WHEN
        when(habitRepository.findAll()).thenReturn(Arrays.asList(habit1, habit2));
        List<Habit> habits = habitService.findAllHabits();

        //THEN
        assertNotNull(habits);
        assertEquals(2, habits.size());
    }

    @Test
    void findHabitById() {

        //GIVEN
        Habit habit = new Habit();
        habit.setId(1L);

        //WHEN
        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));
        Optional<Habit> foundHabit = habitService.findHabitById(1L);

        //THEN
        assertTrue(foundHabit.isPresent());
        assertEquals(1L, foundHabit.get().getId());
    }

    @Test
    void createHabit() {

        //GIVEN
        Habit habit = new Habit();
        habit.setName("NAWYK");

        //WHEN
        when(habitRepository.save(any(Habit.class))).thenReturn(habit);
        Habit result = habitService.createHabit(habit);

        //THEN
        assertNotNull(result);
        assertEquals("NAWYK",result.getName());
    }

    @Test
    void deleteHabit() {

        //GIVEN
        Habit habit = new Habit();
        habit.setId(1L);

        //WHEN
        when(habitRepository.findById(1L)).thenReturn(Optional.of(habit));
        doNothing().when(habitRepository).deleteById(1L);
        boolean isDeleted = habitService.deleteHabit(1L);

        //THEN
        assertTrue(isDeleted);
        verify(habitRepository, times(1)).deleteById(1L);
    }
}