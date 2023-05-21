package models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
    public String name;
    public double lat;
    public double lng;

    public double temperature;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public Station(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Get the last reading from the list of readings.
     * return The last reading or null if the list is empty.
     */
    public Reading getLastReading() {
        if (readings.isEmpty()) {
            return null;
        } else {
            return readings.get(readings.size() - 1);
        }
    }

    /**
     * Get the Beaufort scale label based on the  wind speed parameter.
     * return The Beaufort scale label as an integer.
     */
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

    // Convert the temperature from Celsius to Fahrenheit. Return The temperature in Fahrenheit.
    public double getFahrenheit() {
        return (temperature * 1.8) + 32;
    }

    //Get the compass direction based on the compass direction in degrees. Return The compass direction as a string.
    public static String getCompassDirection(Double compassDirection) {
        String direction = "";

        if (compassDirection != null) {
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
        }
        return direction;

    }

    /**
     * Get the weather icon class based on the weather code. Returning The weather icon class as a string which later is
     * added in the html.
     */
    public static String getWeatherIcon(Integer weatherCode) {
        String iconClass = "fa-solid fa-question fa-beat";

        if (weatherCode != null) {
            switch (weatherCode) {
                case 100:
                    iconClass = "fa-solid fa-sun fa-beat";
                    break;
                case 200:
                    iconClass = "fa-solid fa-cloud-sun fa-beat";
                    break;
                case 300:
                    iconClass = "fa-solid fa-cloud fa-beat";
                    break;
                case 400:
                    iconClass = "fa-solid fa-cloud-showers-water fa-beat";
                    break;
                case 500:
                    iconClass = "fa-solid fa-cloud-showers-heavy fa-beat";
                    break;
                case 600:
                    iconClass = "fa-solid fa-cloud-rain fa-beat";
                    break;
                case 700:
                    iconClass = "fa-solid fa-snowflake fa-beat";
                    break;
                case 800:
                    iconClass = "fa-solid fa-bolt fa-beat";
                    break;
            }
        }
        return iconClass;
    }

    /**
     * Get the temperature icon class based on the temperature value.
     * The temperature icon class as a string which is added further in the html.
     */
    public static String getTemperatureIcon(Double temperature) {
        String iconClass = "fa-solid fa-question fa-beat";
        if (temperature != null) {
            if (temperature < 0) {
                iconClass = "fa-solid fa-thermometer-empty fa-beat";
            } else if (temperature >= 0 && temperature < 10) {
                iconClass = "fa-solid fa-thermometer-quarter fa-beat";
            } else if (temperature >= 10 && temperature < 20) {
                iconClass = "fa-solid fa-thermometer-half fa-beat";
            } else if (temperature >= 20 && temperature < 30) {
                iconClass = "fa-solid fa-thermometer-three-quarters fa-beat";
            } else if (temperature >= 30) {
                iconClass = "fa-solid fa-thermometer-full fa-beat";
            }
        }

        return iconClass;
    }

    /**
     * Get the  icon class with rotation based on the compass direction value.
     * The compass direction class is as a string which is added further in the html.
     */
    public static String getCompassDirectionWithIcon(Double compassDirection) {
        String directionIcon = "fa-solid fa-question fa-beat";
        if (compassDirection != null) {
            if (compassDirection >= 348.75 || compassDirection < 11.25) {
                directionIcon = "fa-solid fa-arrow-up rotate-360";
            } else if (compassDirection >= 11.25 && compassDirection < 33.75) {
                directionIcon = "fa-solid fa-arrow-up rotate-33";
            } else if (compassDirection >= 33.75 && compassDirection < 56.25) {
                directionIcon = "fa-solid fa-arrow-right rotate-45";
            } else if (compassDirection >= 56.25 && compassDirection < 78.75) {
                directionIcon = "fa-solid fa-arrow-down rotate-75";
            } else if (compassDirection >= 78.75 && compassDirection < 101.25) {
                directionIcon = "fa-solid fa-arrow-down rotate-90";
            } else if (compassDirection >= 101.25 && compassDirection < 123.75) {
                directionIcon = "fa-solid fa-arrow-down rotate-115";
            } else if (compassDirection >= 123.75 && compassDirection < 146.25) {
                directionIcon = "fa-solid fa-arrow-left rotate-135";
            } else if (compassDirection >= 146.25 && compassDirection < 168.75) {
                directionIcon = "fa-solid fa-arrow-up rotate-155";
            } else if (compassDirection >= 168.75 && compassDirection < 191.25) {
                directionIcon = "fa-solid fa-arrow-up rotate-180";
            } else if (compassDirection >= 191.25 && compassDirection < 213.75) {
                directionIcon = "fa-solid fa-arrow-up rotate-210";
            } else if (compassDirection >= 213.75 && compassDirection < 236.25) {
                directionIcon = "fa-solid fa-arrow-right rotate-225";
            } else if (compassDirection >= 236.25 && compassDirection < 258.75) {
                directionIcon = "fa-solid fa-arrow-down rotate-250";
            } else if (compassDirection >= 258.75 && compassDirection < 281.25) {
                directionIcon = "fa-solid fa-arrow-down rotate-270";
            } else if (compassDirection >= 281.25 && compassDirection < 303.75) {
                directionIcon = "fa-solid fa-arrow-down rotate-300";
            } else if (compassDirection >= 303.75 && compassDirection < 326.25) {
                directionIcon = "fa-solid fa-arrow-left rotate-315";
            } else if (compassDirection >= 326.25 && compassDirection < 348.75) {
                directionIcon = "fa-solid fa-arrow-up rotate-340";
            }
        }
        return directionIcon + " rotate-" + compassDirection;
    }

    /**
     * Get the minimum temperature recorded among the readings.
     *
     * @return The minimum temperature as a formatted string or "No data" if readings are empty.
     */
    public String getMinTemperature() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double minTemperature = readings.get(0).temperature; // Initialize minTemperature with the first reading's temperature
            for (Reading reading : readings) {
                if (reading.temperature < minTemperature) {
                    minTemperature = reading.temperature; // Update minTemperature if a lower temperature is found
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format the temperature to two decimal places
            return decimalFormat.format(minTemperature);
        }
    }

    /**
     * Get the maximum temperature recorded among the readings.
     *
     * @return The maximum temperature as a formatted string or "No data" if readings are empty.
     */
    public String getMaxTemperature() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double maxTemperature = readings.get(0).temperature; // Initialize maxTemperature with the first reading's temperature
            for (Reading reading : readings) {
                if (reading.temperature > maxTemperature) {
                    maxTemperature = reading.temperature; // Update maxTemperature if a higher temperature is found
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format the temperature to two decimal places
            return decimalFormat.format(maxTemperature);
        }
    }

    /**
     * Get the minimum pressure recorded among the readings.
     * Return the minimum pressure as a string or "No data" if readings are empty.
     */
    public String getMinPressure() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double minPressure = readings.get(0).pressure; // Initialize minPressure with the first reading's pressure
            for (Reading reading : readings) {
                if (reading.pressure < minPressure) {
                    minPressure = reading.pressure; // Update minPressure if a lower pressure is found
                }
            }
            return Double.toString(minPressure);
        }
    }

    /**
     * Get the maximum pressure recorded among the readings.
     *
     * @return The maximum pressure as a string or "No data" if readings are empty.
     */
    public String getMaxPressure() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double maxPressure = readings.get(0).pressure; // Initialize maxPressure with the first reading's pressure
            for (Reading reading : readings) {
                if (reading.pressure > maxPressure) {
                    maxPressure = reading.pressure; // Update maxPressure if a higher pressure is found
                }
            }
            return Double.toString(maxPressure);
        }
    }

    /**
     * Get the minimum wind speed recorded among the readings.
     *
     * @return The minimum wind speed as a formatted string or "No data" if readings are empty.
     */
    public String getMinWindSpeed() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double minWindSpeed = readings.get(0).windSpeed; // Initialize minWindSpeed with the first reading's wind speed
            for (Reading reading : readings) {
                if (reading.windSpeed < minWindSpeed) {
                    minWindSpeed = reading.windSpeed; // Update minWindSpeed if a lower wind speed is found
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format the wind speed to two decimal places
            return decimalFormat.format(minWindSpeed);
        }
    }

    /**
     * Get the maximum wind speed recorded among the readings.
     *
     * @return The maximum wind speed as a formatted string or "No data" if readings are empty.
     */
    public String getMaxWindSpeed() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            float maxWindSpeed = readings.get(0).windSpeed; // Initialize maxWindSpeed with the first reading's wind speed
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWindSpeed) {
                    maxWindSpeed = reading.windSpeed; // Update maxWindSpeed if a higher wind speed is found
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format the wind speed to two decimal places
            return decimalFormat.format(maxWindSpeed);
        }
    }

    public String getLastWindSpeedTrend() {
        int numReadings = readings.size();
        if (numReadings >= 3) {
            //getting the last three readings
            Reading previousReading = readings.get(numReadings - 3);
            Reading middleReading = readings.get(numReadings - 2);
            Reading latestReading = readings.get(numReadings - 1);

            //getting  the windSpeed
            float previousWindSpeed = previousReading.windSpeed;
            float middleWindSpeed = middleReading.windSpeed;
            float latestWindSpeed = latestReading.windSpeed;

            //determining the windSpeed trend
            if (latestWindSpeed > middleWindSpeed && middleWindSpeed > previousWindSpeed) {
                return "fa-solid fa-arrow-up fa-beat"; //arrow-up if trend is increasing
            }
            if (latestWindSpeed < middleWindSpeed && middleWindSpeed < previousWindSpeed) {
                return "fa-solid fa-arrow-down fa-beat"; //arrow-down if trend is decreasing
            }
        }
        return "fa-solid fa-equals fa-beat"; //equal sign if the trend is steady or none of the above
    }

    public String getLastTemperatureTrend() {
        int numReadings = readings.size();
        if (numReadings >= 3) {
            //getting the last three readings
            Reading previousReading = readings.get(numReadings - 3);
            Reading middleReading = readings.get(numReadings - 2);
            Reading latestReading = readings.get(numReadings - 1);
            //getting  the temperature
            float previousTemperature = previousReading.temperature;
            float middleTemperature = middleReading.temperature;
            float latestTemperature = latestReading.temperature;
            //determining the temperature trend
            if (latestTemperature > middleTemperature && middleTemperature > previousTemperature) {
                return "fa-solid fa-arrow-up fa-beat"; //arrow-up if trend is increasing
            } else if (latestTemperature < middleTemperature && middleTemperature < previousTemperature) {
                return "fa-solid fa-arrow-down fa-beat"; //arrow-down if trend is decreasing
            }
        }
        return "fa-solid fa-equals fa-beat"; //equal sign if the trend is steady or none of the above
    }

    public String getLastPressureTrend() {
        int numReadings = readings.size();
        if (numReadings >= 3) {
            //getting the last three readings
            Reading previousReading = readings.get(numReadings - 3);
            Reading middleReading = readings.get(numReadings - 2);
            Reading latestReading = readings.get(numReadings - 1);
            //getting  the pressure
            float previousPressure = previousReading.pressure;
            float middlePressure = middleReading.pressure;
            float latestPressure = latestReading.pressure;
            //determining the pressure trend
            if (latestPressure > middlePressure && middlePressure > previousPressure) {
                return "fa-solid fa-arrow-up fa-beat"; //arrow-up if trend is increasing
            } else if (latestPressure < middlePressure && middlePressure < previousPressure) {
                return "fa-solid fa-arrow-down fa-beat"; //arrow-down if trend is decreasing
            }
        }
        return "fa-solid fa-equals fa-beat"; //equal sign if the trend is steady or none of the above
    }

}
