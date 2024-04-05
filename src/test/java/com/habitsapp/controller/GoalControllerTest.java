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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
//
    void updateGoal_ShouldUpdateGoal() throws Exception {
        given(goalService.updateGoal(any(Long.class), any(Goal.class))).willReturn(goal);

        mockMvc.perform(put("/update/{id}", 1L)
                        .with(user("user").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON) // Określa typ zawartości żądania
                        .accept(MediaType.APPLICATION_JSON) // Wymusza akceptację odpowiedzi JSON
                        .content(objectMapper.writeValueAsString(goal)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(goal)));
    }



    @Test
    @WithMockUser(username="user", roles={"USER"})
    void getAllGoals_ShouldReturnAllGoals() throws Exception {
        List<Goal> allGoals = Collections.singletonList(goal);
        given(goalService.findAllGoals()).willReturn(allGoals);

        mockMvc.perform(get("/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(allGoals)));
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void getGoal_ShouldReturnGoal() throws Exception {
        given(goalService.findGoalById(anyLong())).willReturn(Optional.of(goal));

        mockMvc.perform(get("/getGoal/{id}", 1L)
                     .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(goal)));
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void createGoal_ShouldReturnCreatedGoal() throws Exception {
        given(goalService.createGoal(any(Goal.class))).willReturn(goal);

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goal)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(goal)));
}

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void deleteGoal_ShouldDeleteGoal() throws Exception {
        given(goalService.deleteGoal(anyLong())).willReturn(true);

        mockMvc.perform(delete("/delete/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(goalService, times(1)).deleteGoal(1L);
    }
}
