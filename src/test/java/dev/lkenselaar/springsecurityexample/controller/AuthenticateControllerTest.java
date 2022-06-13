package dev.lkenselaar.springsecurityexample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lkenselaar.springsecurityexample.model.DTO.AuthenticateRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authenticateWithCorrectCredentialsShouldGiveToken() throws Exception {
        AuthenticateRequestDTO authenticateRequest = new AuthenticateRequestDTO();
        authenticateRequest.setUsername("john");
        authenticateRequest.setPassword("password");

        this.mockMvc.perform(post("/authenticate")
                    .content(asJsonString(authenticateRequest))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.accessToken").value(startsWith("ey")));
    }

    @Test
    public void authenticateWithIncorrectCredentialsShouldGiveError() throws Exception {
        AuthenticateRequestDTO authenticateRequest = new AuthenticateRequestDTO();
        authenticateRequest.setUsername("invalid");
        authenticateRequest.setPassword("invalid");

        this.mockMvc.perform(post("/authenticate")
                        .content(asJsonString(authenticateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(content().string("403 FORBIDDEN \"Username/password invalid\""));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
