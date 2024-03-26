package com.habitsapp.dto;

import com.habitsapp.model.Category;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoalStatistics {

    private Category category;
    private long count;
}
