package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Java CI Demo Application Tests")
class JavaCiDemo01ApplicationTests {

	@Test
	@DisplayName("Should load Spring application context successfully")
	void contextLoads() {
		// This test will pass if the Spring context loads successfully
		// No assertions needed - if context fails to load, the test will fail
	}

	@Test
	@DisplayName("Should create WelcomeController instance")
	void shouldCreateWelcomeController() {
		WelcomeController controller = new WelcomeController();
		assert controller != null;
		
		String result = controller.welcome();
		assert result != null;
		assert result.equals("Welcome to Java CI Demo");
	}

}
