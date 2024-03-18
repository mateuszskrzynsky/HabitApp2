package com.habitsapp.service;

import com.habitsapp.model.Habit;
import com.habitsapp.repository.HabitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    /**
     * Find all Habit from db
     * @return The found Habit objects
     */
    public List<Habit> findAllHabits() {
        return habitRepository.findAll();
    }

    /**
     * Retrives a habit by its unique identifier
     * @param id The unique identifier od the habit to retrieve
     * @return The Habit corresponding to the requested habit
     */
    public Optional<Habit> findHabitById(Long id) {
        Optional<Habit> habit = habitRepository.findById(id);
        return habit;
    }

    /**
     * Creates a new Habit
     * @param habit The unique identifier for the new Habit
     * @return The newly created Habit object
     */
    public Habit createHabit(Habit habit) {
        Habit newHabit = new Habit();
        newHabit = habitRepository.save(newHabit);
        return newHabit;
    }

    /**
     * Delete Habit  by its unique identifier
     * @param id The unique identifier of the Habit to delete
     * @return delete existing Habit
     * @throws EntityNotFoundException if no habit is found with provided id
     */
    public boolean deleteHabit(Long id) {
        Optional<Habit> habit = habitRepository.findById(id);
        if (habit.isPresent()){
            habitRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Habit not found");
        }
    }
}
