package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Welcome Controller Tests")
class WelcomeControllerTest {

    private static final String EXPECTED_MESSAGE = "Welcome to Java CI Demo";

    private WelcomeController welcomeController;

    @BeforeEach
    void setUp() {
        welcomeController = new WelcomeController();
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
    @DisplayName("Should return consistent message on multiple calls")
    void shouldReturnConsistentMessage() {
        for (int i = 0; i < 3; i++) {
            String result = welcomeController.welcome();
            assert result.equals(EXPECTED_MESSAGE);
        }
    }

    @Test
    @DisplayName("Should return non-null message")
    void shouldReturnNonNullMessage() {
        String result = welcomeController.welcome();
        assert result != null;
    }

    @Test
    @DisplayName("Should return non-empty message")
    void shouldReturnNonEmptyMessage() {
        String result = welcomeController.welcome();
        assert !result.isEmpty();
    }

    @Test
    @DisplayName("Should return expected message content")
    void shouldReturnExpectedMessageContent() {
        String result = welcomeController.welcome();
        assert result.contains("Welcome");
        assert result.contains("Java");
        assert result.contains("CI");
        assert result.contains("Demo");
    }
}
