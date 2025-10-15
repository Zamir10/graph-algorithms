package graph;

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
}