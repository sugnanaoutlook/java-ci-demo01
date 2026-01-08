package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WelcomeController.class)
@DisplayName("Welcome Controller Tests")
class WelcomeControllerTest {

    private static final String WELCOME_ENDPOINT = "/welcome";
    private static final String EXPECTED_MESSAGE = "Welcome to Java CI Demo";

    @Autowired
    private MockMvc mockMvc;

    private WelcomeController welcomeController;

    @BeforeEach
    void setUp() {
        welcomeController = new WelcomeController();
    }

    @Test
    @DisplayName("Should return welcome message when GET /welcome is called")
    void shouldReturnWelcomeMessage() throws Exception {
        mockMvc.perform(get(WELCOME_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(content().string(EXPECTED_MESSAGE))
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    @DisplayName("Should return welcome message when welcome method is called directly")
    void shouldReturnWelcomeMessageDirectly() {
        String result = welcomeController.welcome();
        
        assert result != null;
        assert result.equals(EXPECTED_MESSAGE);
        assert !result.isEmpty();
    }

    @Test
    @DisplayName("Should handle multiple requests to /welcome endpoint")
    void shouldHandleMultipleRequests() throws Exception {
        for (int i = 0; i < 3; i++) {
            mockMvc.perform(get(WELCOME_ENDPOINT))
                    .andExpect(status().isOk())
                    .andExpect(content().string(EXPECTED_MESSAGE));
        }
    }

    @Test
    @DisplayName("Should return 404 for non-existent endpoints")
    void shouldReturn404ForNonExistentEndpoints() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should handle GET request with query parameters")
    void shouldHandleGetRequestWithQueryParameters() throws Exception {
        mockMvc.perform(get(WELCOME_ENDPOINT).param("test", "value"))
                .andExpect(status().isOk())
                .andExpect(content().string(EXPECTED_MESSAGE));
    }
}
