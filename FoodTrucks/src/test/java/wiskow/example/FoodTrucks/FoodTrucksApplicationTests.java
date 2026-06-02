package wiskow.example.FoodTrucks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import wiskow.example.FoodTrucks.core.RestaurantResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class FoodTrucksApplicationTests {

	@Autowired
	WebTestClient testClient;

	@Test
	 void createSolicitationWithAcceptedCep_201() {
		List<RestaurantResponse> resp = testClient
				.post()
				.uri("/api/nearFF")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(Map.of("cep", "91530010"))
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(RestaurantResponse.class)
				.returnResult().getResponseBody();

		assertThat(resp).isNotNull();
		assertThat(resp).hasSize(5);
	}

	@Test
	void createSolicitationWithNoAcceptedCep_500() {
		WebTestClient.ResponseSpec resp = testClient
				.post()
				.uri("/api/nearFF")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(Map.of("cep", "9153001"))
				.exchange()
				.expectStatus().isEqualTo(500);

	}
}