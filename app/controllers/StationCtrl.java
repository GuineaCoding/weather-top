package controllers;

import java.util.List;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public static void index(Long name) {
        // Find the station by id
        Station station = Station.findById(name);
        Logger.info("Station id = " + name);
        // Render the "station.html" template with the station object
        render("station.html", station);
    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, int pressure, float windDirection) {
        // Create a new Reading object with the provided data
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
        // Find the station by  id
        Station station = Station.findById(id);
        // Add the reading to the station's readings collection
        station.readings.add(reading);
        // Save the changes to the station
        station.save();
        // Redirect the user back to the station page
        redirect("/stations/" + id);
    }
}


