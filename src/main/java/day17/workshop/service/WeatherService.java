package day17.workshop.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import day17.workshop.model.Weather;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    public Optional<Weather> getWeather(String city, String units) throws IOException {
        String apiKey = System.getenv("OPEN_WEATHER_MAP_API_KEY");
        String weatherUrl = UriComponentsBuilder.fromUriString(OPEN_WEATHER_URL)
                .queryParam("q", city.replaceAll(" ", "+"))
                .queryParam("units", units)
                .queryParam("appId", apiKey)
                .toUriString();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
        resp = template.getForEntity(weatherUrl, String.class);
        Weather w = Weather.create(resp.getBody());
        if (w != null)
            return Optional.of(w);
        return Optional.empty();
    }

}
