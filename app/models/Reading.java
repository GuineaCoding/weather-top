package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model
{
    public int code;
    public float temperature;
    public double windSpeed;
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
    public double getWindChill() {
        // Convert temperature to Celsius
        double tempC = this.temperature;
        // Convert wind speed to meters per second
        double windSpeedMps = this.windSpeed;
        // Calculate wind chill using the formula
        double windChill = 13.12 + 0.6215 * tempC - 11.37 * Math.pow(windSpeedMps, 0.16)
                + 0.3965 * tempC * Math.pow(windSpeedMps, 0.16);
        // Convert wind chill back to Celsius
        return windChill;
    }
}