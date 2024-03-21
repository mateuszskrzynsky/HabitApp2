package com.habitsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitsapp.model.*;
import com.habitsapp.service.GoalService;
import com.habitsapp.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HabitController.class)
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitService habitService;

    @Autowired
    private ObjectMapper objectMapper;

    private Habit habit;

    @BeforeEach
    void setUp() {
        habit = new Habit();
        habit.setId(1L);
        habit.setName("Smoking");
        habit.setFrequency(Frequency.DAILY);
        habit.setTrack(true);
        habit.setProgress(Progress.ACTIVE);
    }

//    @Test
//    void getAllHabits_ShouldReturnAllHabits() throws Exception {
//        List<Habit> allHabits = Collections.singletonList(habit);
//        given(habitService.findAllHabits()).willReturn(allHabits);
//
//        mockMvc.perform(get("/habit/getAll")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(allHabits)));
//    }
//
//    @Test
//    void getHabit_ShouldReturnHabit() throws Exception {
//        given(habitService.findHabitById(anyLong())).willReturn(Optional.of(habit));
//
//        mockMvc.perform(get("/habit/getHabit/{id}", 1L)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(habit)));
//    }
//
//    @Test
//    void createHabit_ShouldReturnCreatedHabit() throws Exception {
//        given(habitService.createHabit(any(Habit.class))).willReturn(habit);
//
//        mockMvc.perform(post("/habit/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(habit)))
//                .andExpect(status().isCreated())
//                .andExpect(content().json(objectMapper.writeValueAsString(habit)));
//    }

    @Test
    void deleteHabit_ShouldDeleteHabit() throws Exception {
        given(habitService.deleteHabit(anyLong())).willReturn(true);

        mockMvc.perform(delete("/habit/delete/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(habitService, times(1)).deleteHabit(1L);
    }


}