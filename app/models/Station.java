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

    public Reading getLastReading() {
        if (readings.isEmpty()) {
            return null;
        } else {
            return readings.get(readings.size() - 1);
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

    public String getMinTemperature() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double minTemperature = readings.get(0).temperature;
            for (Reading reading : readings) {
                if (reading.temperature < minTemperature) {
                    minTemperature = reading.temperature;
                }
            }
            return Double.toString(minTemperature);
        }
    }

    public String getMaxTemperature() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double maxTemperature = readings.get(0).temperature;
            for (Reading reading : readings) {
                if (reading.temperature > maxTemperature) {
                    maxTemperature = reading.temperature;
                }
            }
            return Double.toString(maxTemperature);
        }
    }

    public String getMinPressure() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double minPressure = readings.get(0).pressure;
            for (Reading reading : readings) {
                if (reading.pressure < minPressure) {
                    minPressure = reading.pressure;
                }
            }
            return Double.toString(minPressure);
        }
    }

    public String getMaxPressure() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double maxPressure = readings.get(0).pressure;
            for (Reading reading : readings) {
                if (reading.pressure > maxPressure) {
                    maxPressure = reading.pressure;
                }
            }
            return Double.toString(maxPressure);
        }
    }

    public String getMinWindSpeed() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double minWindSpeed = readings.get(0).windSpeed;
            for (Reading reading : readings) {
                if (reading.windSpeed < minWindSpeed) {
                    minWindSpeed = reading.windSpeed;
                }
            }
            return Double.toString(minWindSpeed);
        }
    }

    public String getMaxWindSpeed() {
        if (readings.isEmpty()) {
            return "No data";
        } else {
            double maxWindSpeed = readings.get(0).windSpeed;
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWindSpeed) {
                    maxWindSpeed = reading.windSpeed;
                }
            }
            return Double.toString(maxWindSpeed);
        }
    }
}
