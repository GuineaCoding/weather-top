package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;

    public double windSpeed;

    public double temperature;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    private int code;

    public Station(String name, double windSpeed)
    {
        this.name = name;
        this.windSpeed = windSpeed;
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

}


