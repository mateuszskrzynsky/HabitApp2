package com.habitsapp.controller;

import com.habitsapp.model.Goal;
import com.habitsapp.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goal")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    /**
     * Retrive all added goals
     * @return The requested goals with HTTP status 200
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Goal>> getAllGoals(){
        List<Goal> goals = goalService.findAllGoals();
        return ResponseEntity.ok(goals);
    }

    /**
     * Retrive a goal by it's ID
     * @param id The ID of the goal to retrive
     * @return The requested goal if found, with HTTP status 200, if not found HTTP status 404
     */
    @GetMapping("/getGoal/{id}")
    public ResponseEntity<Goal> getGoal(@PathVariable Long id){
        return goalService.findGoalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Creates a new goal based on the provided data.
     * @param goal request data required to create the goal
     * @return The created goal with HTTP status 201
     */
    @PostMapping("/create")
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal){
        Goal newGoal= goalService.createGoal(goal);
        return new ResponseEntity<>(newGoal, HttpStatus.CREATED);
    }

    /**
     * Delete a goal by its ID
     * @param id The ID of the goal to delete
     * @return HTTP status 204 with no content it the deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable Long id){
        Boolean deleted =  goalService.deleteGoal(id);

        if (deleted){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing Goal with new data
     * @param id The unique identifier of the goal to update
     * @param goal The new goal details in JSON format
     * @return The update Goal object with HTTP status 200
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        Goal updatedGoal = goalService.updateGoal(id, goal);
        return ResponseEntity.ok(updatedGoal);
    }




}
