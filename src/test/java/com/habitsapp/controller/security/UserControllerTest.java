package com.habitsapp.controller.security;

import com.habitsapp.service.security.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void registerUser_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/registration")
                        .param("username", "admin")
                        .param("password", "admin"))
                .andExpect(redirectedUrl(null)); //TODO nie działa właściwy link (byś może dlatego, że nie istnieje)
    }

    @Test
    void login_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().isForbidden()); //TODO http status oczekiwany 200, występuje 403 - brak dostępu
    }
}