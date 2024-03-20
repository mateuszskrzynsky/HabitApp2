package com.habitsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitsapp.controller.GoalController;
import com.habitsapp.model.Category;
import com.habitsapp.model.Goal;
import com.habitsapp.service.GoalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GoalController.class)
class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoalService goalService;

    @Autowired
    private ObjectMapper objectMapper;

    private Goal goal;

    @BeforeEach
    void setUp() {
        goal = new Goal();
        goal.setId(1L);
        goal.setName("Learn Spring Boot");
        goal.setCategory(Category.DIET);
        goal.setStartDate(LocalDate.of(2023, 1, 1));
        goal.setEndDate(LocalDate.of(2023, 12, 31));
    }


    @Test
    void updateGoal_ShouldUpdateGoal() throws Exception {
        given(goalService.updateGoal(any(Long.class), any(Goal.class))).willReturn(goal);

        mockMvc.perform(put("/goal/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON) // Określa typ zawartości żądania
                        .accept(MediaType.APPLICATION_JSON) // Wymusza akceptację odpowiedzi JSON
                        .content(objectMapper.writeValueAsString(goal)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(goal)));
    }


//    @Test
//    void getAllGoals() {
//        ;
//    }
//
//    @Test
//    void getGoal() {
//
//    }
//
//    @Test
//    void createGoal() {
//
//    }
//
//    @Test
//    void deleteGoal() {
//
//    }
}
