package com.habitsapp.service;

import com.habitsapp.model.Goal;
import com.habitsapp.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    /**
     * Find all Goals from db
     * @return The found Goal objects
     */
    public List<Goal> findAllGoals() {
        return goalRepository.findAll();
    }

    /**
     * Retrives a goal by its unique identifier
     * @param id The unique identifier od the goal to retrieve
     * @return The Goal corresponding to the requested goal
     */
    public Optional<Goal> findGoalById(Long id) {
        Optional<Goal> goal = goalRepository.findById(id);
        if (goal.isPresent()){
            goal.get();
        } else {
            throw  new EntityNotFoundException("Goal not found");
        } return goal;
    }


    /**
     * Creates a new Goal
     * @param goal The unique identifier for the new Goal
     * @return The newly created Goal object
     */
    public Goal createGoal(Goal goal) {
        Goal newGoal = new Goal();
        newGoal = goalRepository.save(newGoal);
        return newGoal;

    }

    /**
     * Delete Goal  by its unique identifier
     * @param id The unique identifier of the Goal to delete
     * @return delete existing Goal
     * @throws EntityNotFoundException if no goal is found with provided id
     */
    public boolean deleteGoal(Long id) {
        Optional<Goal> goal = goalRepository.findById(id);
        if (goal.isPresent()){
            goalRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Goal not found");
        }
    }

    /**
     * Updates an existing Goal with new data
     * @param id The unique identifier of the goal to update
     * @param updateGoal The new goal details in JSON format
     * @return The update Goal object
     * @throws RuntimeException if goal not found
     */
    public Goal updateGoal(Long id, Goal updateGoal){
        return goalRepository.findById(id)
                .map(goal->{
                    goal.setName(updateGoal.getName());
                    goal.setCategory(updateGoal.getCategory());
                    goal.setStartDate(updateGoal.getStartDate());
                    goal.setEndDate(updateGoal.getEndDate());
                    return goalRepository.save(goal);
                })
                .orElseThrow(() -> new RuntimeException("Goal do not exists"));
    }

}
