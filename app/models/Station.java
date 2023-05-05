package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
    public String name;

    public double temperature;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public Station(String name) {
        this.name = name;
    }

    public Reading getLastReading() {
        if (readings.isEmpty()) {
            return null;
        } else {
            return readings.get(readings.size() - 1);
        }
    }

    public String getCodeDescription(int code) {
        switch (code) {
            case 100:
                return "Clear";
            case 200:
                return "Partial clouds";
            case 300:
                return "Cloudy";
            case 400:
                return "Light Showers";
            case 500:
                return "Heav Showers";
            case 600:
                return "Rain";
            case 700:
                return "Snow";
            case 800:
                return "Thunder";
            default:
                return "Unknown";
        }
    }
    public int getBeaufortLabel(double windSpeed) {
        if (windSpeed < 1) {
            return 0;
        } else if (windSpeed <= 5) {
            return 1;
        } else if (windSpeed <= 11) {
            return 2;
        } else if (windSpeed <= 19) {
            return 3;
        } else if (windSpeed <= 28) {
            return 4;
        } else if (windSpeed <= 38) {
            return 5;
        } else if (windSpeed <= 49) {
            return 6;
        } else if (windSpeed <= 61) {
            return 7;
        } else if (windSpeed <= 74) {
            return 8;
        } else if (windSpeed <= 88) {
            return 9;
        } else if (windSpeed <= 102) {
            return 10;
        } else if (windSpeed <= 117) {
            return 11;
        } else {
            return 12;
        }
    }

    public double getFahrenheit() {
        return (temperature * 1.8) + 32;
    }

    public static String getCompassDirection(double compassDirection) {
        String direction = "";
        if (compassDirection >= 348.75 || compassDirection < 11.25) {
            direction = "North";
        } else if (compassDirection >= 11.25 && compassDirection < 33.75) {
            direction = "North-Northeast";
        } else if (compassDirection >= 33.75 && compassDirection < 56.25) {
            direction = "Northeast";
        } else if (compassDirection >= 56.25 && compassDirection < 78.75) {
            direction = "East-Northeast";
        } else if (compassDirection >= 78.75 && compassDirection < 101.25) {
            direction = "East";
        } else if (compassDirection >= 101.25 && compassDirection < 123.75) {
            direction = "East-Southeast";
        } else if (compassDirection >= 123.75 && compassDirection < 146.25) {
            direction = "Southeast";
        } else if (compassDirection >= 146.25 && compassDirection < 168.75) {
            direction = "South-Southeast";
        } else if (compassDirection >= 168.75 && compassDirection < 191.25) {
            direction = "South";
        } else if (compassDirection >= 191.25 && compassDirection < 213.75) {
            direction = "South-Southwest";
        } else if (compassDirection >= 213.75 && compassDirection < 236.25) {
            direction = "Southwest";
        } else if (compassDirection >= 236.25 && compassDirection < 258.75) {
            direction = "West-Southwest";
        } else if (compassDirection >= 258.75 && compassDirection < 281.25) {
            direction = "West";
        } else if (compassDirection >= 281.25 && compassDirection < 303.75) {
            direction = "West-Northwest";
        } else if (compassDirection >= 303.75 && compassDirection < 326.25) {
            direction = "Northwest";
        } else if (compassDirection >= 326.25 && compassDirection < 348.75) {
            direction = "North-Northwest";
        }
        return direction;
    }

}
