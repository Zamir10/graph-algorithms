package graph;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT
)
public class ApplicationTests {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void whenInputIsValidThenCycleDetected() {
    webTestClient.post()
        .uri("/graph/hasCycle")
        .bodyValue(new int[][]{{1, 2}, {2, 3}, {3, 1}})
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(Boolean.class).isEqualTo(true);
  }

  @Test
  void whenInputIsInvalidReturnException() {
    webTestClient.post()
        .uri("/graph/hasCycle")
        .bodyValue(new int[][]{{}})
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.message").exists()
        .jsonPath("$.message")
        .value(equalTo("Edge at index 0 must have exactly 2 nodes, but has 0"));
  }

  @Test
  void whenInputIsValidThenReturnShortestPath() {
    webTestClient.post()
        .uri("/graph/shortestPath/1/3")
        .bodyValue(new int[][]{{1, 2, 1}, {1, 3, 5}, {2, 4, 2}, {3, 4, 1}, {4, 5, 3}})
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$[0]").isEqualTo(1)
        .jsonPath("$[1]").isEqualTo(2)
        .jsonPath("$[2]").isEqualTo(4)
        .jsonPath("$[3]").isEqualTo(3);
  }

  @Test
  void whenInputIsValidThenReturnException() {
    webTestClient.post()
        .uri("/graph/shortestPath/1/3")
        .bodyValue(new int[][]{{1, 2, -1}, {1, 3, 5}, {2, 4, 2}})
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.message").exists()
        .jsonPath("$.message")
        .value(equalTo("Edge at index 0 contains negative node values or negative weight: [1, 2, -1]"));
  }
}