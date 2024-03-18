package com.habitsapp.controller;

import com.habitsapp.model.Habit;
import com.habitsapp.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habit")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    /**
     * Retrive all added habits
     * @return The requested habits with HTTP status 200
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Habit>> getAllHabits(){
        List<Habit> habits = habitService.findAllHabits();
        return ResponseEntity.ok(habits);
    }

    /**
     * Retrive a habit by it's ID
     * @param id The ID of the habit to retrive
     * @return The requested habit if found, with HTTP status 200, if not found HTTP status 404
     */
    @GetMapping("/getHabit/{id}")
    public ResponseEntity<Habit> getHabit(@PathVariable Long id){
        return habitService.findHabitById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Creates a new habit based on the provided data.
     * @param habit request data required to create the habit
     * @return The created habit with HTTP status 201
     */
    @PostMapping("/create")
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit){
        Habit newHabit= habitService.createHabit(habit);
        return new ResponseEntity<>(newHabit, HttpStatus.CREATED);
    }

    /**
     * Delete a habit by its ID
     * @param id The ID of the habit to delete
     * @return HTTP status 204 with no content it the deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Habit> deleteHabit(@PathVariable Long id){
        Boolean deleted =  habitService.deleteHabit(id);

        if (deleted){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
