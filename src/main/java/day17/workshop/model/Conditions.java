package day17.workshop.model;

import java.io.Serializable;

import jakarta.json.JsonObject;

public class Conditions implements Serializable {

    private String description;

    private String icon;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static Conditions createJson(JsonObject o) {
        Conditions c = new Conditions();
        c.description = "%s - %s".formatted(o.getString("main"),
                o.getString("description"));
        c.icon = "http://openweathermap.org/img/wn/%s@4x.png".formatted(o.getString("icon"));
        return c;
    }

}
