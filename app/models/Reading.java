package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model
{
    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;

    public float windDirection;

    public Reading(int code, float temperature, float windSpeed, int pressure, float windDirection)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }
    public float getTemperatureInFahrenheit() {
        return (this.temperature * 9/5) + 32;
    }
}