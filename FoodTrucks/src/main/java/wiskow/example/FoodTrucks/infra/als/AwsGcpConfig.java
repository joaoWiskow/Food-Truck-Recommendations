package wiskow.example.FoodTrucks.infra.als;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.geoplaces.GeoPlacesClient;

@Configuration
public class AwsGcpConfig {

    @Bean
    public GeoPlacesClient geoPlacesClient() {
        String minhaKey = "";
        String minhaSecret = "";

        return GeoPlacesClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(minhaKey, minhaSecret)))
                .build();
    }
}
