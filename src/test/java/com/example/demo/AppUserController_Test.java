package com.example.demo;

import com.example.demo.controllers.AppUserController;
import com.example.demo.dao.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Import(AppUserController.class)
@WebMvcTest(AppUserController.class)
public class AppUserController_Test {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void listAllUsersTest() throws Exception {
        var user = new AppUser();
        user.setUsername("name");
        List<AppUser> users = Arrays.asList(user);
        given(appUserRepository.findAll()).willReturn(users);
        mockMvc.perform(get("/app-users")
                        .contentType(APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[0].username", is(user.getUsername())));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    public void createUserTest() throws Exception {
        AppUser newUser = new AppUser();
        newUser.setUsername("CREATED_USER");

        given(appUserRepository.save(any(AppUser.class)))
                .willReturn(newUser);

        mockMvc.perform(post("/app-users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(newUser.getUsername())));
    }
}
