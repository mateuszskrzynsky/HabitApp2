package com.habitsapp.repository;

import com.habitsapp.dto.GoalStatistics;
import com.habitsapp.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal,Long> {

    @Query("SELECT new com.habitsapp.dto.GoalStatistics(g.category, COUNT(g)) FROM Goal g GROUP BY g.category")
    List<GoalStatistics> countGoalsByCategory();

}
