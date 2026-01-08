package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Java CI Demo Application Integration Tests")
class JavaCiDemo01ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private WelcomeController welcomeController;

	@Test
	@DisplayName("Should load Spring application context successfully")
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
		assertThat(welcomeController).isNotNull();
	}

	@Test
	@DisplayName("Should start application on random port")
	void shouldStartOnRandomPort() {
		assertThat(port).isGreaterThan(0);
	}

	@Test
	@DisplayName("Should respond to welcome endpoint with correct message")
	void shouldRespondToWelcomeEndpoint() {
		String url = "http://localhost:" + port + "/welcome";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Welcome to Java CI Demo");
	}

	@Test
	@DisplayName("Should return 404 for non-existent endpoints")
	void shouldReturn404ForNonExistentEndpoints() {
		String url = "http://localhost:" + port + "/nonexistent";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	@DisplayName("Should have WelcomeController bean in application context")
	void shouldHaveWelcomeControllerBean() {
		assertThat(applicationContext.containsBean("welcomeController")).isTrue();
		WelcomeController controller = applicationContext.getBean(WelcomeController.class);
		assertThat(controller).isNotNull();
		assertThat(controller.welcome()).isEqualTo("Welcome to Java CI Demo");
	}

}
