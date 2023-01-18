package day17.workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Weather implements Serializable {

    private String city;
    private String temperature;

    public List<Conditions> conditions = new LinkedList<Conditions>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public List<Conditions> getConditions() {
        return conditions;
    }

    public void setConditions(List<Conditions> conditions) {
        this.conditions = conditions;
    }

    public static Weather create(String json) throws IOException {
        Weather w = new Weather();
        try (
                InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            w.setCity(o.getString("name"));
            w.setTemperature(o.getJsonObject("main").getJsonNumber("temp").toString());
            w.conditions = o.getJsonArray("weather")
                    .stream()
                    .map(v -> (JsonObject) v)
                    .map(v -> Conditions.createJson(v))
                    .toList();

        }
        return w;
    }
}
