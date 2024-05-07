package com.levyferreira.authapi.controllers;

import com.jayway.jsonpath.JsonPath;
import com.levyferreira.authapi.domain.user.UserDTO;
import com.levyferreira.authapi.domain.user.RegisterDTO;
import com.levyferreira.authapi.domain.user.UserRole;
import com.levyferreira.authapi.helper.JsonHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.levyferreira.authapi.domain.user.UserRole.ADMIN;
import static com.levyferreira.authapi.domain.user.UserRole.USER;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnTokenWhenLoginWithValidCredentials() throws Exception {

        if (registerUser(USER).is2xxSuccessful()) {
            UserDTO authDTO = new UserDTO("test@test.com", "password");
            mockMvc.perform(post("/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonHelper.asJsonString(authDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").exists());
        }
    }

    @Test
    void shouldReturnBadRequestWhenLoginWithInvalidCredentials() throws Exception {
        UserDTO authDTO = new UserDTO("invalid@test.com", "password");
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(authDTO)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldCreateUser() throws Exception {
        registerUser(USER);
    }


    @Test
    void shouldCreateAdmin() throws Exception {
        registerUser(ADMIN);
    }

    @Test
    void shouldReturnUsersWhenGetUsers() throws Exception {
        registerUser(ADMIN);
        String token = loginUser(ADMIN);

        mockMvc.perform(get("/user")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists());
    }

    private HttpStatusCode registerUser(UserRole role) throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", "password", role);
        return HttpStatusCode.valueOf(
                mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(registerDTO)))
                .andReturn().getResponse().getStatus());
    }

    private String loginUser(UserRole role) throws Exception {
        UserDTO authDTO = new UserDTO("test@test.com", "password");
        MvcResult result = mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(authDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        return JsonPath.read(response, "$.token");
    }
}